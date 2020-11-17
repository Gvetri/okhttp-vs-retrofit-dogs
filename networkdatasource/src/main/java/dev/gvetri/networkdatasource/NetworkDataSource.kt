package dev.gvetri.networkdatasource

import arrow.core.Either
import dev.gvetri.api.ApiService
import dev.gvetri.apimodel.ApiDummyClass
import dev.gvetri.apimodel.DogApi
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.serializer
import okhttp3.*
import java.io.IOException

//This is supressed because a false positive from OkHttp.
//URL: https://medium.com/@elizarov/callbacks-and-kotlin-flows-2b53aa2525cf
@Suppress("BlockingMethodInNonBlockingContext")
data class NetworkDataSource(
    private val apiService: ApiService,
    private val mapper: (ApiDummyClass?) -> MyDummyClass = ::apiDummyToDummy,
    private val dogMapper: (DogApi?) -> Dog = ::apiDogToDogMapper,
    private val okHttpClient: OkHttpClient
) : DataSource {

    private val dummyUrl = "https://random.dog/woof.json"

    override fun retrieveData(): Flow<Either<AppError, Dog>> =
        callbackFlow<Either<AppError,Dog>> {
            val request = Request.Builder()
                .get()
                .url(dummyUrl)
                .build()
            val call = okHttpClient.newCall(request)

            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    sendBlocking(Either.left(AppError(500, "Error message", e)))
                    close(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseJson = response.body?.string() ?: ""
                    response.body?.close()
                    try {
                        val dogApiFromResponse = decodeFromString<DogApi>(
                            deserializer = serializer(),
                            string = responseJson
                        )
                        sendBlocking(Either.right(apiDogToDogMapper(dogApiFromResponse)))
                    } catch (exception: SerializationException) {
                        sendBlocking(Either.left(AppError(500, "Error message", exception)))
                        close(exception)
                    }
                }
            })
            awaitClose()
        }

    // This example shows how we can use retrofit to model our own response without the OkHttpRequest Boilerplate
    // First we create a service to retrieve the data using the ApiClass which is the response from the server
    // Secondly we convert that class from the server into a domain one using our mappers
    // This help us to convert the data as we want to use into our domain, an example of this could be
    // Forcing the nullable booleans to false
    // Converting from Boolean? to Boolean
    // This also help us to create a defense against backend changes that were not documented.
    // For example if the backend change their contract and instead of sending an empty list send a null we can control it.
    // Finally we emit a Either which is a Disjunction type from Functional programming library Arrow but we can also use a Sealed class.

    suspend fun retrieveRetrofitData(): Flow<Either<AppError, MyDummyClass>> = flow {
        val serviceResult = apiService.retrieveSomeData()
        when {
            serviceResult.isSuccessful -> {
                val either =
                    Either.conditionally(serviceResult.body() == null,
                        ifFalse = { AppError(404, "No body found") },
                        ifTrue = { mapper(serviceResult.body()!!) })
                emit(either)
            }
            else -> {
                emit(Either.left(AppError(serviceResult.code(), serviceResult.message())))
            }
        }
    }

}
