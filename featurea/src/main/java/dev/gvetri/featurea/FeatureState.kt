package dev.gvetri.featurea

import dev.gvetri.model.AppError
import dev.gvetri.model.Dog

sealed class FeatureState {
    data class Success(val result: Dog) : FeatureState()
    data class Error(val error: AppError) : FeatureState()
    object Loading : FeatureState()
}