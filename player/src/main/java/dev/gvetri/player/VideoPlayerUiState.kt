package dev.gvetri.player

import dev.gvetri.model.VideoItem

sealed class VideoPlayerUiState {
    data class PlayVideo(val id: String) : VideoPlayerUiState()
    object VideoIdNotValid : VideoPlayerUiState()
    object Loading : VideoPlayerUiState()
    object VideoDetailError : VideoPlayerUiState()
    data class VideoDetailSuccess(val videoItem: VideoItem) : VideoPlayerUiState()
    data class OrientationChanged(val orientation: Orientation) : VideoPlayerUiState()
    object FullScreenMode : VideoPlayerUiState()
}