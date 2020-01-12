package com.sdos.supermarket.data.repository

import com.sdos.supermarket.domain.model.User

interface UserRepository {
    suspend fun getUser(userId: String): User
    suspend fun addUsers(usersList: List<User>)
    suspend fun getUserByTask(taskCode: String): List<User>
}