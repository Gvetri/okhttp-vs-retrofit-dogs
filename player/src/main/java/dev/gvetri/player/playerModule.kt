package dev.gvetri.player

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    viewModel { PlayerViewModel() }
}