package com.sdos.supermarket.data.repository

import com.sdos.supermarket.data.room.SupermarketDatabase
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.repository.TaskRepository

class TaskRepositoryImpl(private val supermarketDatabase: SupermarketDatabase) :
    TaskRepository {

    override suspend fun addBaseTasks(baseTaskList: List<BaseTask>) {
        baseTaskList.forEach {
            supermarketDatabase.baseTaskDao().insert(it)
        }
    }

    override suspend fun getBaseTasks() =
        supermarketDatabase.baseTaskDao().getAllTasks()

    override suspend fun getPendingTasksByUser(userId: String) =
        supermarketDatabase.taskDao().getUncompletedTasksByUser(userId)

    override suspend fun addTask(task: Task) {
        supermarketDatabase.taskDao().insert(task)
    }

    override suspend fun getUserTasks(userId: String) =
        supermarketDatabase.taskDao().getAllTasksByUser(userId)

    override suspend fun updateTask(task: Task) {
        supermarketDatabase.taskDao().update(task)
    }
}
