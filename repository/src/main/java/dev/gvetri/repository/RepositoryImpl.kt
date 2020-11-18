package dev.gvetri.repository

import arrow.core.Either
import dev.gvetri.apublic.Repository
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val dataSource: DataSource) : Repository {
    override fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>> =
        dataSource.retrieveVideoList()
}