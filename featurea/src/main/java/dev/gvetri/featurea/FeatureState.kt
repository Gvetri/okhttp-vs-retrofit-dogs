package dev.gvetri.featurea

import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.PaginatedVideoList

sealed class FeatureState {
    data class Success(val result: PaginatedVideoList) : FeatureState()
    data class Error(val error: AppError) : FeatureState()
    object Loading : FeatureState()
}