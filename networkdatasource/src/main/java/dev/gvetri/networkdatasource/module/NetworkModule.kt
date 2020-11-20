package dev.gvetri.networkdatasource.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.gvetri.api.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal const val BASE_URL = "https://api.dailymotion.com/"
internal const val API_SERVICE = "ApiService"
internal const val RETROFIT = "Retrofit"
const val HTTPCLIENT = "HttpClient"
internal const val HTTP_CLIENT_LOG_INTERCEPTOR = "LoggerInterceptor"

val networkModule = module {
    single(named(HTTP_CLIENT_LOG_INTERCEPTOR)) { provideInterceptor() }
    single(named(HTTPCLIENT)) {
        provideHttpclient(
            interceptor = get(
                named(
                    HTTP_CLIENT_LOG_INTERCEPTOR
                )
            )
        )
    }
    single(named(RETROFIT)) { provideRetrofit(client = get(named(HTTPCLIENT))) }
    single(named(API_SERVICE)) { provideApiService(retrofit = get(named(RETROFIT))) }
}

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(contentType = "application/json".toMediaType())
        )
        .build()
}

private fun provideHttpclient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient()
    .newBuilder()
    .connectTimeout(240, TimeUnit.SECONDS)
    .readTimeout(240, TimeUnit.SECONDS)
    .addInterceptor(interceptor)
    .build()

private fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
