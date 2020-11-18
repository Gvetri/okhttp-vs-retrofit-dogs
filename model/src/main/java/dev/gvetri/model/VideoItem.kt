package dev.gvetri.model

const val NON_AVAILABLE = "Non Available"

data class VideoItem(
    val channel: String = NON_AVAILABLE,
    val id: String,
    val owner: String = NON_AVAILABLE,
    val title: String = NON_AVAILABLE,
    val thumbnailUrl: String?
)