package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.repository.TaskRepository
import com.sdos.supermarket.domain.repository.UserRepository
import com.sdos.supermarket.domain.enum.TaskType
import com.sdos.supermarket.domain.enum.UserType
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateDataBaseInteractor constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : Interactor<Unit, Unit>() {

    private val usersList = listOf<User>(
        User(
            "ana.alonso",
            "Ana",
            UserType.TECHNICIAN.name,
            listOf(TaskType.REPONEDOR.name, TaskType.COBRAR.name)
        ),
        User(
            "aurora.abad",
            "Aurora",
            UserType.TECHNICIAN.name,
            listOf(TaskType.ENVOLVER.name, TaskType.COBRAR.name)
        ),
        User("rita.ramos", "Rita", UserType.MANAGER.name, listOf()),
        User("ramon.rodriguez", "Ramon", UserType.MANAGER.name, listOf())
    )

    private val tasksList = listOf<BaseTask>(
        BaseTask(TaskType.ENVOLVER.name, "Envolver regalos"),
        BaseTask(TaskType.COBRAR.name, "Cobrar en caja"),
        BaseTask(TaskType.REPONEDOR.name, "Reponer producto")
    )

    override suspend fun execute(request: Unit) =
        withContext(Dispatchers.IO) { createDataBase() }

    private suspend fun createDataBase(): Either<Unit, Throwable> {
        userRepository.addUsers(usersList)
        taskRepository.addBaseTasks(tasksList)
        return Either.Success(Unit)
    }
}

