package com.alanvan.repository.service

import com.alanvan.repository.data.model.AuthResponse
import io.reactivex.Single
import retrofit2.http.POST

interface LSAuthService {
    @POST
    fun getAuthToken(): Single<AuthResponse>
}