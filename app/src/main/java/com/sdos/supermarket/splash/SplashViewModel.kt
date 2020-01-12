package com.sdos.supermarket.splash

import androidx.lifecycle.*
import com.sdos.supermarket.domain.interactor.CreateDataBaseInteractor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModelFactory(private val viewModel: SplashViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
}

class SplashViewModel(
    private val createDataBaseInteractor: CreateDataBaseInteractor
) : ViewModel() {

    private val _dataBaseReady: MutableLiveData<Boolean> = MutableLiveData()
    val dataBaseReady: LiveData<Boolean> = _dataBaseReady

    fun onViewReady() {
        viewModelScope.launch {
            createDataBaseInteractor.execute(Unit)
            _dataBaseReady.value = true
        }
    }
}
