package dev.gvetri.fake

import arrow.core.Either
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import kotlinx.coroutines.flow.Flow

class FakeNetworkDataSource : DataSource {
    override  fun retrieveData(): Flow<Either<AppError, Dog>> {
        TODO("Not yet implemented")
    }
}