package dev.gvetri.di

import dev.gvetri.apublic.REPOSITORY_IMPL
import dev.gvetri.apublic.Repository
import dev.gvetri.networkdatasource.module.NETWORK_DATASOURCE
import dev.gvetri.repository.RepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository>(named(REPOSITORY_IMPL)) {
        RepositoryImpl(dataSource = get(named(NETWORK_DATASOURCE)))
    }
}