package com.alanvan.repository.repository

import com.alanvan.domain.model.Auth
import com.alanvan.domain.repository.LSAccountRepository
import com.alanvan.repository.data.injection.Modules
import com.alanvan.repository.service.LSAuthService
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LSApiAccountRepository : LSAccountRepository, KodeinAware {
    override val kodein: Kodein = Modules.container

    private val authService: LSAuthService by instance()

    override fun getAuthToken(): Single<Auth> {
        return authService.getAuthToken().map {
            Auth(
                accessToken = it.access_token,
                expiryTime = System.currentTimeMillis() + it.expires_in * 1_000L
            )
        }
    }

}