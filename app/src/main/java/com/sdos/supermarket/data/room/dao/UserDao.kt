package com.sdos.supermarket.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdos.supermarket.domain.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE `userId` == :id")
    fun getUserById(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE capabilities LIKE :taskCode")
    fun getUsersByTask(taskCode: String): List<User>
}