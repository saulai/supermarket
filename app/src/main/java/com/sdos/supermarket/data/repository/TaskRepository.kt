package com.sdos.supermarket.data.repository

import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.Task

interface TaskRepository {
    suspend fun addBaseTasks(baseTaskList: List<BaseTask>)
    suspend fun getBaseTasks(): List<BaseTask>
    suspend fun getPendingTasksByUser(userId: String): List<Task>
    suspend fun addTask(task: Task)
    suspend fun getUserTasks(userId: String): List<Task>
    suspend fun updateTask(task: Task)
}