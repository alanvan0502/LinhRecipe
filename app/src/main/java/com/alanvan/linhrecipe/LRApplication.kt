package com.alanvan.linhrecipe

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alanvan.linhrecipe.data.injection.Modules
import com.alanvan.linhrecipe.features.account.AccountManager
import com.alanvan.repository.service.APIBuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein

class LRApplication : Application(), Application.ActivityLifecycleCallbacks {

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
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        )
        APIBuildConfig.UserConfig.fatSecretClientId = BuildConfig.FatSecretClientID
        APIBuildConfig.UserConfig.fatSecretClientSecret = BuildConfig.FatSecretClientSecret
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        AccountManager.cleanUp()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }
}