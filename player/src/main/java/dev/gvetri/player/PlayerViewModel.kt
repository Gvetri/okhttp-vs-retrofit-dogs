package dev.gvetri.player

import android.content.res.Configuration
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gvetri.apublic.VideoPlayerUseCase
import dev.gvetri.model.AppError
import dev.gvetri.model.VideoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayerViewModel(private val useCase: VideoPlayerUseCase) : ViewModel() {

    private val _stateDataFlow: MutableStateFlow<VideoPlayerUiState> =
        MutableStateFlow(VideoPlayerUiState.Loading)

    val stateDataFlow: StateFlow<VideoPlayerUiState> = _stateDataFlow

    fun loadVideo(id: String?) {
        if (id == null) {
            _stateDataFlow.value = VideoPlayerUiState.VideoIdNotValid
        } else {
            _stateDataFlow.value = VideoPlayerUiState.PlayVideo(id)
            retrieveVideoInfo(id)
        }
    }

    private fun retrieveVideoInfo(id: String) {
        viewModelScope.launch {
            useCase.retrieveVideoData(id).collect { response ->
                response.fold(::isLeft, ::isRight)
            }
        }
    }

    private fun isLeft(appError: AppError) {
        _stateDataFlow.value = VideoPlayerUiState.VideoDetailError
    }

    private fun isRight(videoItem: VideoItem) {
        _stateDataFlow.value = VideoPlayerUiState.VideoDetailSuccess(videoItem)
    }

    fun orientationChanged(orientation: Orientation) {
        _stateDataFlow.value = VideoPlayerUiState.OrientationChanged(orientation)
    }

    fun fullScreenToggle() {
        Log.d("TAG", "ViewModel called")
        _stateDataFlow.value = VideoPlayerUiState.FullScreenMode
    }

}