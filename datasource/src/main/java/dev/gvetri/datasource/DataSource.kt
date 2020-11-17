package dev.gvetri.datasource

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun retrieveData(): Flow<Either<AppError, Dog>>
}