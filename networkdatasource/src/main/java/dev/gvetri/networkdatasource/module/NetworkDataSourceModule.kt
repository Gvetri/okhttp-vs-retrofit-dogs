package dev.gvetri.networkdatasource.module

import dev.gvetri.datasource.DataSource
import dev.gvetri.networkdatasource.NetworkDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NETWORK_DATASOURCE = "NetworkDataSource"
val networkDataSourceModule = module {
    single<DataSource>(named(NETWORK_DATASOURCE)) {
        NetworkDataSource(
            apiService = get(named(API_SERVICE)),
            okHttpClient = get(named(HTTPCLIENT))
        )
    }
}