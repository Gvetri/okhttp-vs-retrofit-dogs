package dev.gvetri.model

data class PaginatedVideoList(
    val explicit: Boolean,
    val hasMore: Boolean,
    val limit: Int,
    val list: List<VideoItem>,
    val page: Int,
    val total: Int
)