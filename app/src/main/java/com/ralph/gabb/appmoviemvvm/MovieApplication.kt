package com.ralph.gabb.appmoviemvvm

import android.app.Application
import com.ralph.gabb.appmoviemvvm.di.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * Created by Ralph Gabrielle Orden on 9/20/2019.
 */
class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        plantDebugTree()
        configureDependencyInjection()
    }

    private fun configureDependencyInjection() {
        startKoin(this, appModule)
    }

    private fun plantDebugTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}