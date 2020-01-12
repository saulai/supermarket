package com.sdos.supermarket.data.cache

import com.sdos.supermarket.domain.model.Product

class ProductsCache(private val products: MutableList<Product> = mutableListOf()) {

    fun isLoaded() = products.isNotEmpty()

    fun loadAll(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
    }

    fun getAll() = products

    fun removeAll() = products.clear()
}
