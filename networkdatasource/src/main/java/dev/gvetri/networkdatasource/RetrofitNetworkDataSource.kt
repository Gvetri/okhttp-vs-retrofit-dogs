package dev.gvetri.networkdatasource

import arrow.core.Either
import dev.gvetri.api.ApiService
import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.AppError
import dev.gvetri.model.PaginatedVideoList
import dev.gvetri.model.VideoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val notFoundErrorCode = 404

private const val NOT_FOUND_ERROR_MESSAGE = "No Video was found with that id"

class RetrofitNetworkDataSource(
    private val apiService: ApiService,
    private val videoListMapper: (PaginatedVideoListApi?) -> PaginatedVideoList = ::paginatedVideoListMapper,
    private val videoDetailMapper: (VideoItemApi?) -> VideoItem? = ::videoItemApiMapper,
) : DataSource {
    override fun retrieveVideoList(): Flow<Either<AppError, PaginatedVideoList>> = flow {
        val retrofitCall = apiService.retrieveNewsFeed()
        if (retrofitCall.isSuccessful && retrofitCall.body() != null) {
            emit(Either.right(videoListMapper(retrofitCall.body())))
        } else {
            emit(Either.left(AppError(retrofitCall.code(), retrofitCall.message())))
        }
    }

    override fun retrieveVideoDetail(id: String): Flow<Either<AppError, VideoItem>> = flow {
        val retrofitCall = apiService.retrieveVideoDetailID(id)
        if (retrofitCall.isSuccessful && retrofitCall.body() != null) {
            val domainModel = videoDetailMapper(retrofitCall.body())
            Either.conditionally(
                test = domainModel != null,
                ifFalse = {
                    emit(Either.left(AppError(notFoundErrorCode, NOT_FOUND_ERROR_MESSAGE)))
                },
                ifTrue = {
                    emit(Either.Right(domainModel!!))
                }
            )
        } else {
            emit(Either.left(AppError(retrofitCall.code(), retrofitCall.message())))
        }
    }
}