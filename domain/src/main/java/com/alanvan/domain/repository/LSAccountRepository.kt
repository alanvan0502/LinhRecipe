package com.alanvan.domain.repository

import com.alanvan.domain.model.Auth
import io.reactivex.Single

interface LSAccountRepository {
    fun getAuthToken(): Single<Auth>
}