package dev.gvetri.networkdatasource

import arrow.core.Either
import dev.gvetri.api.ApiService
import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.*
import kotlinx.coroutines.channels.awaitClose
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
    private val videoListMapper: (PaginatedVideoListApi?) -> PaginatedVideoList = ::paginatedVideoListMapper,
    private val videoDetailMapper: (VideoItemApi?) -> VideoItem? = ::videoItemApiMapper,
    private val okHttpClient: OkHttpClient
) : DataSource {

    private val videoListUrl =
        "https://api.dailymotion.com/videos?channel=news&fields=thumbnail_240_url%2Cid%2Ctitle%2Cchannel%2Cowner"


    override fun retrieveVideoList(): Flow<Either<AppError, PaginatedVideoList>> =
        callbackFlow {
            val request = Request.Builder()
                .get()
                .url(videoListUrl)
                .build()
            val call = okHttpClient.newCall(request)

            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    offer(Either.left(AppError(500, "Error message", e)))
                    close(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseJson = response.body?.string() ?: ""
                    response.body?.close()
                    try {
                        val paginatedVideoListApi = decodeFromString<PaginatedVideoListApi>(
                            deserializer = serializer(),
                            string = responseJson
                        )
                        offer(Either.right(videoListMapper(paginatedVideoListApi)))
                    } catch (exception: SerializationException) {
                        offer(Either.left(AppError(500, "Error message", exception)))
                        close(exception)
                    }
                }
            })
            awaitClose()
        }

    override fun retrieveVideoDetail(id: String): Flow<Either<AppError, VideoItem>> =
        callbackFlow {
            val detailUrl =
                "https://api.dailymotion.com/video/${id}?fields=thumbnail_240_url%2Cid%2Ctitle%2Cchannel%2Cowner"
            val request = Request.Builder()
                .get()
                .url(detailUrl)
                .build()
            val call = okHttpClient.newCall(request)

            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    offer(Either.left(AppError(500, "Error message", e)))
                    close(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseJson = response.body?.string() ?: ""
                    response.body?.close()
                    try {
                        val videoItemApi = decodeFromString<VideoItemApi>(
                            deserializer = serializer(),
                            string = responseJson
                        )
                        if (videoDetailMapper(videoItemApi) != null) {
                            offer(Either.right(videoDetailMapper(videoItemApi)))
                        } else {
                            offer(
                                Either.left(
                                    AppError(
                                        404,
                                        "Request Successful but no item found"
                                    )
                                )
                            )
                        }
                    } catch (exception: SerializationException) {
                        offer(Either.left(AppError(500, "Error message", exception)))
                        close(exception)
                    }
                }
            })
            awaitClose()
        }

}
