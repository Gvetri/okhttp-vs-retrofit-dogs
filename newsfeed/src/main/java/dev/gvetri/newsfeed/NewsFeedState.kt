package dev.gvetri.newsfeed

import dev.gvetri.model.AppError
import dev.gvetri.model.PaginatedVideoList

sealed class NewsFeedState {
    data class Success(val result: PaginatedVideoList) : NewsFeedState()
    data class Error(val error: AppError) : NewsFeedState()
    object Loading : NewsFeedState()
}