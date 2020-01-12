package com.sdos.supermarket.login

import androidx.lifecycle.*
import com.sdos.supermarket.domain.model.User
import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.interactor.LoginInteractor
import kotlinx.coroutines.launch

class LoginViewModelFactory(private val viewModel: LoginViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
}

class LoginViewModel(val loginInteractor: LoginInteractor) : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _error: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _error

    fun onLoginClicked(user: String, password: String) {
        viewModelScope.launch {
            val loginEither = loginInteractor(LoginInteractor.Request(user, password))
            when (loginEither ) {
                is Either.Success -> {
                    _user.value = loginEither.content
                }
                is Either.Error -> {
                    _error.value = true
                }
            }
        }
    }
}