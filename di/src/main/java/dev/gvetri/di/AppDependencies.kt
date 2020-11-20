package dev.gvetri.di

import dev.gvetri.apublic.REPOSITORY_IMPL
import dev.gvetri.apublic.Repository
import dev.gvetri.networkdatasource.module.NETWORK_DATASOURCE
import dev.gvetri.networkdatasource.module.RETROFIT_NETWORK_DATASOURCE
import dev.gvetri.repository.RepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository>(named(REPOSITORY_IMPL)) {
        RepositoryImpl(dataSource = get(named(NETWORK_DATASOURCE)))
    }

    // TODO uncomment this to try the NetworkDataSource that uses the Retrofit request :)
    // NOTE: If you do that don't forget to comment the declaration above, otherwise it will say that there's already a repository declared
//    single<Repository>(named(REPOSITORY_IMPL)) {
//        RepositoryImpl(dataSource = get(named(RETROFIT_NETWORK_DATASOURCE)))
//    }
}