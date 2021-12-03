package com.schaefer.catganisation

import android.app.Application
import com.schaefer.catganisation.di.appModule
import com.schaefer.core.di.networkModule
import com.schaefer.home.di.breedModule
import com.schaefer.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class CatganisationApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogs()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CatganisationApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    loginModule,
                    breedModule,
                )
            )
        }
    }

    private fun setupLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //TODO put Crashlytics
        }
    }
}