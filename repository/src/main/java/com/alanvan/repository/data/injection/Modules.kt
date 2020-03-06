package com.alanvan.repository.data.injection

import com.alanvan.repository.service.APIBuildConfig
import com.alanvan.repository.service.LSAuthService
import com.alanvan.repository.service.ServiceBuilder
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object Modules {

    enum class Tag {
        FS_BASE_URL,
        FS_BASE_AUTH_URL,
        FS_CLIENT_ID,
        FS_CLIENT_SECRET
    }

    private val production = Kodein.Module("Production") {
        bind<Kodein>() with singleton { kodein }
        bind<String>(Tag.FS_BASE_URL) with singleton { "https://platform.fatsecret.com" }
        bind<String>(Tag.FS_BASE_AUTH_URL) with singleton { "https://oauth.fatsecret.com" }
        bind<String>(Tag.FS_CLIENT_ID) with singleton { APIBuildConfig.UserConfig.fatSecretClientId }
        bind<String>(Tag.FS_CLIENT_SECRET) with singleton { APIBuildConfig.UserConfig.fatSecretClientSecret }

        bind<LSAuthService>() with singleton {
            ServiceBuilder.createLSAuthService(
                instance(Tag.FS_BASE_AUTH_URL),
                instance(Tag.FS_CLIENT_ID),
                instance(Tag.FS_CLIENT_SECRET)
            )
        }
    }

    val container by lazy {
        Kodein {
            import(production, allowOverride = true)
        }
    }
}