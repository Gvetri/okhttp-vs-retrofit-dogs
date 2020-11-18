package dev.gvetri.featurea

import android.util.Log
import arrow.core.Either
import dev.gvetri.apublic.FeatureUseCase
import dev.gvetri.apublic.Repository
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class FeatureUseCaseImpl(
    private val repository: Repository
) : FeatureUseCase {
    override fun retrieveData(): Flow<Either<AppError, PaginatedVideoList>> =
        repository.retrieveData().flowOn(Dispatchers.IO)
}