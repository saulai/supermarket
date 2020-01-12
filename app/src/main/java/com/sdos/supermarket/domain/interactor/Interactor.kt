package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.common.Either


abstract class Interactor<I, O> {

    abstract suspend fun execute(request: I): Either<O, Throwable>

    suspend operator fun invoke(request: I) = execute(request)
}
