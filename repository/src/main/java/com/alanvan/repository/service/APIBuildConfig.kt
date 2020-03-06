package com.alanvan.repository.service

import okhttp3.Interceptor

object APIBuildConfig {

    object UserConfig {
        var fatSecretClientId: String = ""
        var fatSecretClientSecret: String = ""
    }

    object OkHttpConfig {
        val APPLICATION_INTERCEPTORS = mutableListOf<Interceptor>()
    }
}
