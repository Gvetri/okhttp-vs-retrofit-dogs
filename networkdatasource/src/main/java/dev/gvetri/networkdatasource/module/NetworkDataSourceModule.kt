package dev.gvetri.networkdatasource.module

import dev.gvetri.datasource.DataSource
import dev.gvetri.networkdatasource.NetworkDataSource
import dev.gvetri.networkdatasource.RetrofitNetworkDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NETWORK_DATASOURCE = "NetworkDataSource"
const val RETROFIT_NETWORK_DATASOURCE = "RetrofitNetworkDataSource"
val networkDataSourceModule = module {

    single<DataSource>(named(NETWORK_DATASOURCE)) {
        NetworkDataSource(
            okHttpClient = get(named(HTTPCLIENT))
        )
    }

    single<DataSource>(named(RETROFIT_NETWORK_DATASOURCE)) {
        RetrofitNetworkDataSource(
            apiService = get(named(API_SERVICE))
        )
    }
}