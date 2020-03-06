package com.alanvan.linhrecipe

import android.app.Application
import com.alanvan.linhrecipe.data.injection.Modules
import com.alanvan.repository.service.APIBuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein

class LRApplication : Application() {

    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        initializeModules()
        initializeAPI()
    }

    private fun initializeModules() {
        Modules.application = this
        kodein = Modules.container
    }

    private fun initializeAPI() {
        APIBuildConfig.OkHttpConfig.APPLICATION_INTERCEPTORS.add(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
        )
    }
}