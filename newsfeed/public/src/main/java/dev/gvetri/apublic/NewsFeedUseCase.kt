package dev.gvetri.apublic

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.Flow

interface NewsFeedUseCase {
    fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>>
}