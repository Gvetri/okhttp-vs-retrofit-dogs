package dev.gvetri.apimodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedVideoListApi(
    @SerialName("explicit")
    val explicit: Boolean? = null,
    @SerialName("has_more")
    val hasMore: Boolean? = null,
    @SerialName("limit")
    val limit: Int? = null,
    @SerialName("list")
    val list: List<VideoItemApi>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("total")
    val total: Int? = null
)