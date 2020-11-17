package dev.gvetri.codingtest

import android.app.Application
import dev.gvetri.di.repositoryModule
import dev.gvetri.featurea.featureAModule
import dev.gvetri.networkdatasource.module.networkDataSourceModule
import dev.gvetri.networkdatasource.module.networkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                featureAModule,
                repositoryModule,
                networkModule,
                networkDataSourceModule
            )
        }
    }
}