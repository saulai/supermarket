package com.sdos.supermarket.common.di

import com.sdos.supermarket.data.cache.ProductsCache
import org.koin.dsl.module

val cacheModule = module {

    fun provideProductsCache() = ProductsCache()

    single<ProductsCache> { provideProductsCache() }
}