package com.ankit.mvvmtodo.KoinInjection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppKoin:Application() {
    override fun onCreate() {
        super.onCreate()
            startKoin {
                androidContext(this@AppKoin)
                        modules(viewModelModule)
                        modules(databaseModule)
                        modules(repositoryModule)
            }
    }
}