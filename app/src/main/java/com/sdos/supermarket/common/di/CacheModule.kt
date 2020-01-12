package com.sdos.supermarket.common.di

import com.sdos.supermarket.data.cache.ProductsCache
import com.sdos.supermarket.data.repository.ProductsRepository
import com.sdos.supermarket.data.repository.TaskRepository
import com.sdos.supermarket.data.repository.UserRepository
import com.sdos.supermarket.domain.interactor.*
import org.koin.dsl.module

val cacheModule = module {

    fun provideProductsCache() = ProductsCache()

    single<ProductsCache> { provideProductsCache() }
}