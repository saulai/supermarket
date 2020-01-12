package com.sdos.supermarket.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.model.BaseTask

@Dao
interface BaseTaskDao {
    @Query("SELECT * FROM base_task")
    fun getAllTasks(): List<BaseTask>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(baseTask: BaseTask)
}