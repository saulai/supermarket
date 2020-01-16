package com.sdos.supermarket.data.repository

import com.sdos.supermarket.data.room.SupermarketDatabase
import com.sdos.supermarket.domain.model.User
import com.sdos.supermarket.domain.repository.UserRepository

class UserRepositoryImpl(private val supermarketDatabase: SupermarketDatabase) :
    UserRepository {
    override suspend fun getUser(userId: String): User {
        return supermarketDatabase.userDao().getUserById(userId)
    }

    override suspend fun addUsers(usersList: List<User>) {
        usersList.forEach {
            supermarketDatabase.userDao().insert(it)
        }
    }

    override suspend fun getUserByTask(taskCode: String): List<User> =
        supermarketDatabase.userDao().getUsersByTask("%$taskCode%")
}
