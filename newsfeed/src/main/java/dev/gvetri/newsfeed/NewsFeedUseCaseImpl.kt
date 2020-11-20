package dev.gvetri.newsfeed

import arrow.core.Either
import dev.gvetri.apublic.NewsFeedUseCase
import dev.gvetri.apublic.Repository
import dev.gvetri.model.AppError
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class NewsFeedUseCaseImpl(
    private val repository: Repository
) : NewsFeedUseCase {
    override fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>> =
        repository.retrieveData().flowOn(Dispatchers.IO)
}