package dev.gvetri.networkdatasource

import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.model.*


fun paginatedVideoListMapper(paginatedVideoListApi: PaginatedVideoListApi?): PaginatedVideoList {
    val limitFallbackValue = 10
    val pageFallbackValue = 0
    val totalFallbackValue = 20

    val videoList = paginatedVideoListApi?.list?.mapNotNull { item ->
        videoItemApiMapper(item)
    } ?: emptyList()

    return PaginatedVideoList(
        explicit = paginatedVideoListApi?.explicit ?: false,
        hasMore = paginatedVideoListApi?.hasMore ?: false,
        limit = paginatedVideoListApi?.limit ?: limitFallbackValue,
        list = videoList,
        page = paginatedVideoListApi?.page ?: pageFallbackValue,
        total = paginatedVideoListApi?.total ?: totalFallbackValue
    )

}

fun videoItemApiMapper(videoItemApi: VideoItemApi?): VideoItem? {
    if (videoItemApi == null) return null
    return VideoItem(
        channel = videoItemApi.channel ?: NON_AVAILABLE,
        id = videoItemApi.id ?: "",
        owner = videoItemApi.owner ?: NON_AVAILABLE,
        title = videoItemApi.title ?: NON_AVAILABLE,
        thumbnailUrl = videoItemApi.thumbnail_240_url
    )
}