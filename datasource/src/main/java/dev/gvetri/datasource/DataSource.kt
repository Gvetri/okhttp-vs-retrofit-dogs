package dev.gvetri.datasource

import arrow.core.Either
import dev.gvetri.model.*
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun retrieveVideoList(): Flow<Either<AppError, PaginatedVideoList>>
    fun retrieveVideoDetail(id: String): Flow<Either<AppError, VideoItem>>
}