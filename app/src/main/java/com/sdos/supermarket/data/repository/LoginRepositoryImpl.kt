package com.sdos.supermarket.data.repository

import com.sdos.supermarket.domain.repository.LoginRepository

class LoginRepositoryImpl(
) : LoginRepository {

    val userMap = mapOf<String, String>(
        Pair("ana.alonso", "tecnico"),
        Pair("aurora.abad", "tecnico"),
        Pair("rita.ramos", "gestor"),
        Pair("ramon.rodriguez", "gestor")
    )

    override suspend fun performLogin(user: String, password: String): Boolean {
        return userMap.filter { it.key == user }.containsValue(password)
    }

}