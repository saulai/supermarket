package com.sdos.supermarket.domain.interactor

import com.sdos.supermarket.domain.repository.ProductsRepository
import com.sdos.supermarket.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeachesInteractor constructor(
    private val productsRepository: ProductsRepository
) : Interactor<Unit, List<Product>>() {

    override suspend fun execute(request: Unit) =
        withContext(Dispatchers.IO) { getBaseTasks() }

    private suspend fun getBaseTasks() = productsRepository.getPeaches()
}

