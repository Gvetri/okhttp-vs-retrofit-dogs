package dev.gvetri.datasource

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun retrieveVideoList(): Flow<Either<AppError, PaginatedVideoList>>
}