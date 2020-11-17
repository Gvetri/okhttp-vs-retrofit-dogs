package dev.gvetri.apimodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogApi(
    @SerialName("fileSizeBytes")
    val fileSizeBytes: Int? = null,
    @SerialName("url")
    val url: String? = null
)