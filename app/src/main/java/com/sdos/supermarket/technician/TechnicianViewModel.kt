package com.sdos.supermarket.technician

import androidx.lifecycle.*
import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.interactor.GetUserInteractor
import com.sdos.supermarket.domain.interactor.GetUserTasksInteractor
import com.sdos.supermarket.domain.interactor.SetCompletedTasksInteractor
import com.sdos.supermarket.domain.model.Task
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.launch

class TechnicianViewModelFactory(private val viewModel: TechnicianViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
}

class TechnicianViewModel(
    private val getUserTasksInteractor: GetUserTasksInteractor,
    private val getUserInteractor: GetUserInteractor,
    private val setCompletedTaskInteractor: SetCompletedTasksInteractor
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun onViewReady(userId: String) {
        getUserInformation(userId)
        getUserTasks(userId)

    }

    private fun getUserInformation(userId: String) {
        viewModelScope.launch {
            _user.value =
                getUserInteractor(GetUserInteractor.Request(userId)).let { if (it is Either.Success) it.content else null }
        }
    }

    private fun getUserTasks(userId: String) {
        viewModelScope.launch {
            _tasks.value =
                getUserTasksInteractor(GetUserTasksInteractor.Request(userId)).let { if (it is Either.Success) it.content else null }
        }
    }

    fun onTaskCompleted(task: Task, taskCompletion: Boolean) {
        viewModelScope.launch {
            setCompletedTaskInteractor(SetCompletedTasksInteractor.Request(task, taskCompletion))
        }
    }
}
