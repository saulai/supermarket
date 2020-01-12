package com.sdos.supermarket.common


abstract class Interactor<I, O> {

    abstract suspend fun execute(request: I): Either<Throwable, O>

    suspend operator fun invoke(request: I) = execute(request)
}
