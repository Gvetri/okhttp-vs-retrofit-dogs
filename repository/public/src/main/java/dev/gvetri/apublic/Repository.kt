package dev.gvetri.apublic

import arrow.core.Either
import dev.gvetri.model.*
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>>
    fun retrieveVideoDetail(id: String): Flow<Either<AppError, VideoItem>>
}