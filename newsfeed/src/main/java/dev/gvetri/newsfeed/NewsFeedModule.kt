package dev.gvetri.newsfeed

import dev.gvetri.apublic.NewsFeedUseCase
import dev.gvetri.apublic.REPOSITORY_IMPL
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NEWS_FEED_USE_CASE = "NewsFeedUseCase"
val newsFeedModule = module {
    single<NewsFeedUseCase>(named(NEWS_FEED_USE_CASE)) {
        NewsFeedUseCaseImpl(get(named(REPOSITORY_IMPL)))
    }

    viewModel { NewsFeedViewModel(useCase = get(named(NEWS_FEED_USE_CASE))) }
}