package dev.gvetri.apublic

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.VideoItem
import kotlinx.coroutines.flow.Flow

interface VideoPlayerUseCase {

    fun retrieveVideoData(id: String): Flow<Either<AppError, VideoItem>>
}