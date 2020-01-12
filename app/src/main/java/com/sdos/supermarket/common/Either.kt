package com.sdos.supermarket.common

sealed class Either<out E, out V> {
    data class Success<out V>(val content: V) : Either<V, Nothing>()
    data class Error<out E>(val error: E) : Either<Nothing, E>()
}