package dev.gvetri.networkdatasource

import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.model.*


/***
 * Converts a Network Model to a Domain Model discarding the null items from the list
 * @return a list of PaginatedVideoList if there are valid VideoItems otherwise returns an emptyList
 */
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

/***
 * Converts a VideoItemApi Network Model to a Domain Model discarding the items without Id
 * Used !! because the smart cast doesn't work when the model is in a different module
 * @return a VideoItem object if the id exist otherwise return null
 */
fun videoItemApiMapper(videoItemApi: VideoItemApi?): VideoItem? {
    return if (videoItemApi?.id == null) null
    else {
        VideoItem(
            channel = videoItemApi.channel ?: NON_AVAILABLE,
            id = videoItemApi.id!!,
            owner = videoItemApi.owner ?: NON_AVAILABLE,
            title = videoItemApi.title ?: NON_AVAILABLE,
            thumbnailUrl = videoItemApi.thumbnail_240_url
        )
    }
}