package dev.gvetri.model

data class AppError(
    val errorCode: Int,
    val errorMessage: String,
    val errorCause: Throwable? = null
)
