package dev.gvetri.fake

import arrow.core.Either
import dev.gvetri.datasource.DataSource
import dev.gvetri.model.*
import kotlinx.coroutines.flow.Flow

class FakeNetworkDataSource : DataSource {
    override fun retrieveVideoList(): Flow<Either<AppError, PaginatedVideoList>> {
        TODO("Not yet implemented")
    }

    override fun retrieveVideoDetail(id: String): Flow<Either<AppError, VideoItem>> {
        TODO("Not yet implemented")
    }
}