package com.alanvan.repository.service

import com.alanvan.repository.data.model.account.AuthResponse
import io.reactivex.Single
import retrofit2.http.POST

interface LSAuthService {
    @POST("/connect/token")
    fun getAuthToken(): Single<AuthResponse>
}