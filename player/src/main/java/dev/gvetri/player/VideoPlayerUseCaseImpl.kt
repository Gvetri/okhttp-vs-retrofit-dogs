package dev.gvetri.player

import arrow.core.Either
import dev.gvetri.apublic.Repository
import dev.gvetri.apublic.VideoPlayerUseCase
import dev.gvetri.model.AppError
import dev.gvetri.model.VideoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class VideoPlayerUseCaseImpl(private val repository: Repository) : VideoPlayerUseCase {
    override fun retrieveVideoData(id: String): Flow<Either<AppError, VideoItem>> =
        repository.retrieveVideoDetail(id).flowOn(Dispatchers.IO)

}