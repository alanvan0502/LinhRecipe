package com.alanvan.domain.model

data class Auth(
    val accessToken: String,
    val expiresIn: Long
)