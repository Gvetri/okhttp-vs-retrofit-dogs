package dev.gvetri.featurea

import androidx.lifecycle.*
import dev.gvetri.apublic.FeatureUseCase
import dev.gvetri.model.AppError
import dev.gvetri.model.Dog
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FeatureAViewModel(useCase: FeatureUseCase) : ViewModel() {

    private val _stateDataFlow: MutableStateFlow<FeatureState> =
        MutableStateFlow(FeatureState.Loading)

    val stateDataFlow: StateFlow<FeatureState> = _stateDataFlow

    init {
        viewModelScope.launch {
            useCase.retrieveData()
                .collect { _stateDataFlow.value = it.fold(::onError, ::onSuccess) }
        }
    }

    private fun onError(appError: AppError): FeatureState = FeatureState.Error(appError)

    private fun onSuccess(videoList: PaginatedVideoList): FeatureState = FeatureState.Success(videoList)
}