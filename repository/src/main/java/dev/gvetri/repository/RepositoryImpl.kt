package dev.gvetri.repository

import android.util.Log
import arrow.core.Either
import dev.gvetri.apublic.Repository
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun retrieveData(): Flow<Either<AppError, Dog>> =
        dataSource.retrieveData()
}