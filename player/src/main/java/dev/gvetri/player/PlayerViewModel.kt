package dev.gvetri.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {

    private val _stateDataFlow: MutableStateFlow<VideoPlayerUiState> =
        MutableStateFlow(VideoPlayerUiState.Loading)

    val stateDataFlow: StateFlow<VideoPlayerUiState> = _stateDataFlow

    fun loadVideo(id: String?) {
        if (id == null) {
            _stateDataFlow.value = VideoPlayerUiState.VideoIdNotValid
        } else {
            _stateDataFlow.value = VideoPlayerUiState.PlayVideo(id)
        }
    }

}