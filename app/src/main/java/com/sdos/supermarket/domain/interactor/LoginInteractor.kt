package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.repository.LoginRepository
import com.sdos.supermarket.domain.repository.UserRepository
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginInteractor constructor(
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository
) : Interactor<LoginInteractor.Request, User>() {

    override suspend fun execute(request: Request) =
        withContext(Dispatchers.IO) { performLogin(request) }

    private suspend fun performLogin(request: Request) =
        when (val loginOk = loginRepository.performLogin(request.user, request.password)) {
            loginOk -> Either.Success(userRepository.getUser(request.user))
            else -> Either.Error(Throwable())
        }

    data class Request(val user: String, val password: String)
}

