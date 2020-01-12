package com.sdos.supermarket.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.model.User

@Dao
interface TaskDao {
    @Query("SELECT * FROM task WHERE userCode LIKE :userCode")
    fun getAllTasksByUser(userCode : String): List<Task>

    @Query("SELECT * FROM task WHERE userCode LIKE :userCode AND completed == :completed")
    fun getUncompletedTasksByUser(userCode : String, completed: Boolean = false): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}