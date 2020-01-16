package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.repository.TaskRepository
import com.sdos.supermarket.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserTasksInteractor constructor(
    private val taskRepository: TaskRepository
) : Interactor<GetUserTasksInteractor.Request, List<Task>>() {

    override suspend fun execute(request: Request) =
        withContext(Dispatchers.IO) { getTasks(request) }

    private suspend fun getTasks(request: Request) =
        Either.Success(taskRepository.getUserTasks(request.userId))

    data class Request(val userId: String)
}

