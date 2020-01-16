package com.sdos.supermarket.domain.repository

interface LoginRepository {
    suspend fun performLogin(user: String, password: String): Boolean
}