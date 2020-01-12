package com.sdos.supermarket.data.repository

interface LoginRepository {
    suspend fun performLogin(user: String, password: String): Boolean
}