package dev.gvetri.api

import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val NEWS_FEED_ENDPOINT = "videos"
private const val VIDEO_DETAIL_ENDPOINT = "video/{id}"
private const val CHANNEL_QUERY_PARAMS = "channel"
private const val CHANNEL_DEFAULT_VALUE = "news"
private const val CHANNEL_FIELD_PARAMS = "fields"
private const val CHANNEL_FIELD_DEFAULT_VALUE = "thumbnail_240_url,id,title,channel,owner"
private const val PATH_ID_VALUE = "id"

interface ApiService {

    @GET(NEWS_FEED_ENDPOINT)
    suspend fun retrieveNewsFeed(
        @Query(CHANNEL_QUERY_PARAMS) channel: String = CHANNEL_DEFAULT_VALUE,
        @Query(CHANNEL_FIELD_PARAMS) fields: String = CHANNEL_FIELD_DEFAULT_VALUE
    ): Response<PaginatedVideoListApi>

    @GET(VIDEO_DETAIL_ENDPOINT)
    suspend fun retrieveVideoDetailID(
        @Path(PATH_ID_VALUE) id: String,
        @Query(CHANNEL_FIELD_PARAMS) fields: String = CHANNEL_FIELD_DEFAULT_VALUE
    ): Response<VideoItemApi>
}