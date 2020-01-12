package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.data.repository.TaskRepository
import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.model.BaseTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseTasksInteractor constructor(
    private val taskRepository: TaskRepository
) : Interactor<Unit, List<BaseTask>>() {

    override suspend fun execute(request: Unit) =
        withContext(Dispatchers.IO) { getBaseTasks() }

    private suspend fun getBaseTasks() =
        Either.Success(taskRepository.getBaseTasks())
}

