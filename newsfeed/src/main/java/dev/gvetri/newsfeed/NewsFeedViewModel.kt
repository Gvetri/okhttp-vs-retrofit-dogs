package dev.gvetri.newsfeed

import androidx.lifecycle.*
import dev.gvetri.apublic.NewsFeedUseCase
import dev.gvetri.model.AppError
import dev.gvetri.model.PaginatedVideoList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsFeedViewModel(useCase: NewsFeedUseCase) : ViewModel() {

    private val _stateDataFlow: MutableStateFlow<NewsFeedState> =
        MutableStateFlow(NewsFeedState.Loading)

    val stateDataFlow: StateFlow<NewsFeedState> = _stateDataFlow

    init {
        viewModelScope.launch {
            useCase.retrieveData()
                .collect { _stateDataFlow.value = it.fold(::onError, ::onSuccess) }
        }
    }

    private fun onError(appError: AppError): NewsFeedState = NewsFeedState.Error(appError)

    private fun onSuccess(videoList: PaginatedVideoList): NewsFeedState = NewsFeedState.Success(videoList)
}