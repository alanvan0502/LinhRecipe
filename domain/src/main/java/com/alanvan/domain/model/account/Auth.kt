package com.alanvan.domain.model.account

data class Auth(
    val accessToken: String,
    val expiresIn: Long
)