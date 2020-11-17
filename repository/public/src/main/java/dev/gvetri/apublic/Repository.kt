package dev.gvetri.apublic

import arrow.core.Either
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun retrieveData(): Flow<Either<AppError, Dog>>
}