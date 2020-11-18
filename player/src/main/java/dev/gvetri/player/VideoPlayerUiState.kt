package dev.gvetri.player

sealed class VideoPlayerUiState {
    data class PlayVideo(val id: String) : VideoPlayerUiState()
    object VideoIdNotValid : VideoPlayerUiState()
    object Loading : VideoPlayerUiState()

}