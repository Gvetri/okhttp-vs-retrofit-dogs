package dev.gvetri.apimodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoItemApi(
    @SerialName("channel")
    val channel: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("owner")
    val owner: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("thumbnail_240_url")
    val thumbnail_240_url: String? = null
)