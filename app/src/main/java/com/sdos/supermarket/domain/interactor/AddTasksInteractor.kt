package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.common.getTotalWorkLoad
import com.sdos.supermarket.domain.repository.TaskRepository
import com.sdos.supermarket.domain.repository.UserRepository
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddTasksInteractor constructor(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) : Interactor<AddTasksInteractor.Request, User?>() {

    override suspend fun execute(request: Request) =
        withContext(Dispatchers.IO) { addTask(request) }

    private suspend fun addTask(request: Request): Either.Success<User?> {
        val availableUsers = userRepository.getUserByTask(request.baseTask.code)
        val minimumWorkLoadUser = getMinimumWorkloadUser(availableUsers)


        taskRepository.addTask(
            Task(
                description = request.description,
                hours = request.duration.toInt(),
                taskType = request.baseTask.code,
                userCode = minimumWorkLoadUser
            )
        )
        return Either.Success(availableUsers.find { it.userId == minimumWorkLoadUser })
    }

    private suspend fun getMinimumWorkloadUser(
        availableUsers: List<User>
    ): String {

        val workLoadMatrix = mutableMapOf<String, Int>()
        availableUsers.forEach { user ->
            val userTasks =
                taskRepository.getPendingTasksByUser(user.userId)
            workLoadMatrix[user.userId] = userTasks.getTotalWorkLoad(user.userId)
        }

        return workLoadMatrix.toList()
            .sortedBy { (_, value) -> value }
            .toMap().entries.first().key
    }

    data class Request(val baseTask: BaseTask, val description: String, val duration: String)
}

