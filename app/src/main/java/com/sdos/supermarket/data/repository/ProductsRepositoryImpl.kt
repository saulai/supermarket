package com.sdos.supermarket.data.repository

import com.sdos.supermarket.common.Either
import com.sdos.supermarket.data.api.ProductsApiClient
import com.sdos.supermarket.data.cache.ProductsCache
import com.sdos.supermarket.domain.model.Product
import com.sdos.supermarket.domain.repository.ProductsRepository

const val CATEGORY_FRUIT = "Fruit"
const val ITEM_PEACHES = "Peaches"

class ProductsRepositoryImpl(
    private val productsApiClient: ProductsApiClient,
    private val productsCache: ProductsCache
) : ProductsRepository {

    override suspend fun getPeaches() =
        when (productsCache.isLoaded()) {
            true -> Either.Success(productsCache.getAll())
            else -> when (val responseEither =
                productsApiClient.getProduct(CATEGORY_FRUIT, ITEM_PEACHES)) {
                is Either.Success -> {
                    val productsList = responseEither.content.map {
                        Product(
                            farmName = it.farmName,
                            farmerId = it.farmerId,
                            phoneNumber = it.phoneNumber,
                            zipCode = it.zipCode,
                            category = it.category,
                            item = it.item,
                            business = it.business
                        )
                    }
                    productsCache.loadAll(productsList)
                    Either.Success(productsCache.getAll())
                }
                is Either.Error -> responseEither
            }
        }
}
