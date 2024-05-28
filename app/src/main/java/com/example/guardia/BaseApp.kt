package com.example.guardia

import android.app.Application
import com.example.guardia.di.modules.presentationModule
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    presentationModule
                )
            )
        }
    }
}