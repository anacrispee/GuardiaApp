package com.example.guardia

import android.app.Application
import com.example.guardia.di.modules.dataModule
import com.example.guardia.di.modules.dataRemoteValue
import com.example.guardia.di.modules.domainModule
import com.example.guardia.di.modules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(
                listOf(
                    presentationModule,
                    dataRemoteValue,
                    dataModule,
                    domainModule
                )
            )
            androidContext(applicationContext)
        }
    }
}