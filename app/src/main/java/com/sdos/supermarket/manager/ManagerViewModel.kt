package com.sdos.supermarket.manager

import androidx.lifecycle.*
import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.interactor.AddTasksInteractor
import com.sdos.supermarket.domain.interactor.BaseTasksInteractor
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.launch

class ManagerViewModelFactory(private val viewModel: ManagerViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
}

class ManagerViewModel(
    private val baseTasksInteractor: BaseTasksInteractor,
    private val addTasksInteractor: AddTasksInteractor
) : ViewModel() {

    private val _baseTasks = MutableLiveData<List<BaseTask>>()
    val baseTasks : LiveData<List<BaseTask>> = _baseTasks

    private val _taskAssignedToUser: MutableLiveData<String> = MutableLiveData()
    val taskAsignedToUser: LiveData<String> = _taskAssignedToUser

    init {
        viewModelScope.launch {
            _baseTasks.value =
                baseTasksInteractor(Unit).let { if (it is Either.Success) it.content else null }
        }
    }

    fun onAddTaskClicked(baseTask: BaseTask, description: String, duration: String) {
        viewModelScope.launch {
            val either =
                addTasksInteractor(AddTasksInteractor.Request(baseTask, description, duration))
            when (either){
                is Either.Success<User?> -> _taskAssignedToUser.value = either.content?.firstName
            }
            _taskAssignedToUser.value = null
        }
    }
}
