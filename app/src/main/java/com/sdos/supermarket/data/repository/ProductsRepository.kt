package com.sdos.supermarket.data.repository

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.model.BaseTask
import com.sdos.supermarket.domain.model.Product
import com.sdos.supermarket.domain.model.Task

interface ProductsRepository {
    suspend fun getPeaches(): Either<List<Product>, Throwable>
}