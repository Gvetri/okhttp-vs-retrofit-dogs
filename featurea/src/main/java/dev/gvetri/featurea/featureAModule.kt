package dev.gvetri.featurea

import dev.gvetri.apublic.FeatureUseCase
import dev.gvetri.apublic.REPOSITORY_IMPL
import dev.gvetri.apublic.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.Definitions
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FEATURE_USE_CASE_IMPL = "VideoListUseCase"
val featureAModule = module {
    single<FeatureUseCase>(named(FEATURE_USE_CASE_IMPL)) {
        FeatureUseCaseImpl(get(named(REPOSITORY_IMPL)))
    }

    viewModel { FeatureAViewModel(useCase = get(named(FEATURE_USE_CASE_IMPL))) }
}