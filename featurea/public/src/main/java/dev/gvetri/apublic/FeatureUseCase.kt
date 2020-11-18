package dev.gvetri.apublic

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.Flow

interface FeatureUseCase {
    fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>>
}