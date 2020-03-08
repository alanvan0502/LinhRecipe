package com.alanvan.repository.service

import com.squareup.moshi.Moshi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val TIME_OUT_SECONDS: Long = 120

    private val moshi: Moshi = Moshi.Builder().build()

    fun createLSAuthService(baseUrl: String, userName: String, password: String): LSAuthService =
        createAuthRetrofit(baseUrl, userName, password).create(LSAuthService::class.java)

    fun createLSService(baseUrl: String): LSService =
        createRetrofit(baseUrl).create(LSService::class.java)

    private fun createAuthRetrofit(baseUrl: String, userName: String, password: String): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()

                val formData = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("grant_type", "client_credentials")
                    .addFormDataPart("scope", "basic")
                    .build()

                val requestBuilder = original.newBuilder()
                    .header("Authorization", Credentials.basic(userName, password))
                    .header("Content-type", "application/json")
                    .post(formData)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptors(APIBuildConfig.OkHttpConfig.APPLICATION_INTERCEPTORS)

        return clientBuilder.build().buildRetrofit(
            moshi = moshi,
            baseUrl = baseUrl
        )
    }

    private fun createRetrofit(baseUrl: String): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val token = original.url.queryParameter("token")
                val request = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .header("Content-type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptors(APIBuildConfig.OkHttpConfig.APPLICATION_INTERCEPTORS)

        return clientBuilder.build().buildRetrofit(
            moshi = moshi,
            baseUrl = baseUrl
        )
    }

    private fun OkHttpClient.buildRetrofit(moshi: Moshi, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(this)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}

fun OkHttpClient.Builder.addInterceptors(interceptors: MutableList<Interceptor>): OkHttpClient.Builder {
    interceptors.forEach { addInterceptor(it) }
    return this
}