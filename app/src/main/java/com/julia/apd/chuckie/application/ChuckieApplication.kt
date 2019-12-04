package com.julia.apd.chuckie.application

import android.app.Application
import com.julia.apd.chuckie.module.chuckNorrisServerModules
import com.julia.apd.chuckie.module.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ChuckieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChuckieApplication)
            modules(
                listOf(
                    chuckNorrisServerModules,
                    viewModelModules
                )
            )
        }
    }
}