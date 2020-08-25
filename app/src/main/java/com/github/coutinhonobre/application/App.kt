package com.github.coutinhonobre.application

import android.app.Application
import com.github.coutinhonobre.dependency.apiModule
import com.github.coutinhonobre.dependency.repositoryModule
import com.github.coutinhonobre.dependency.retrofitModule
import com.github.coutinhonobre.dependency.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}