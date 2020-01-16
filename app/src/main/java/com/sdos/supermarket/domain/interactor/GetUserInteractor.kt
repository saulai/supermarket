package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.repository.UserRepository
import com.sdos.supermarket.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserInteractor constructor(
    private val userRepository: UserRepository
) : Interactor<GetUserInteractor.Request, User>() {

    override suspend fun execute(request: Request) =
        withContext(Dispatchers.IO) { addTask(request) }

    private suspend fun addTask(request: Request) =
        Either.Success(userRepository.getUser(request.userId))

    data class Request(val userId: String)
}

