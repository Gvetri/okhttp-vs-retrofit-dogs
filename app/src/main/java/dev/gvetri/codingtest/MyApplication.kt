package dev.gvetri.codingtest

import android.app.Application
import dev.gvetri.di.repositoryModule
import dev.gvetri.newsfeed.newsFeedModule
import dev.gvetri.networkdatasource.module.networkDataSourceModule
import dev.gvetri.networkdatasource.module.networkModule
import dev.gvetri.player.playerModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                playerModule,
                newsFeedModule,
                repositoryModule,
                networkModule,
                networkDataSourceModule
            )
        }
    }
}