package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.data.repository.TaskRepository
import com.sdos.supermarket.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetCompletedTasksInteractor constructor(
    private val taskRepository: TaskRepository
) : Interactor<SetCompletedTasksInteractor.Request, List<Task>>() {

    override suspend fun execute(request: Request) =
        withContext(Dispatchers.IO) {
            setCompletedTask(request.task, request.taskCompletion)
            returnTaskList(request.task)
        }


    private suspend fun setCompletedTask(task: Task, taskCompletion: Boolean) {
        taskRepository.updateTask(Task(
            code = task.code,
            description = task.description,
            hours = task.hours,
            taskType = task.taskType,
            userCode = task.userCode,
            completed = taskCompletion
        ))
    }


    private suspend fun returnTaskList(task: Task) =
        Either.Success(taskRepository.getUserTasks(task.userCode))


    data class Request(val task: Task, val taskCompletion: Boolean)

}
