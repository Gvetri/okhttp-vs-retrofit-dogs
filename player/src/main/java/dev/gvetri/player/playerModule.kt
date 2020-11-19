package dev.gvetri.player

import dev.gvetri.apublic.REPOSITORY_IMPL
import dev.gvetri.apublic.VideoPlayerUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val VIDEO_PLAYER_USECASE = "VideoPlayerUseCase"
val playerModule = module {
    single<VideoPlayerUseCase>(named(VIDEO_PLAYER_USECASE)) {
        VideoPlayerUseCaseImpl(get(named(REPOSITORY_IMPL)))
    }
    viewModel { PlayerViewModel(useCase = get(named(VIDEO_PLAYER_USECASE))) }
}