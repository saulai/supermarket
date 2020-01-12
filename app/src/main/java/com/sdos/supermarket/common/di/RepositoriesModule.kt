package com.sdos.supermarket.common.di

import com.sdos.supermarket.data.api.ProductsApiClient
import com.sdos.supermarket.data.cache.ProductsCache
import com.sdos.supermarket.data.repository.*
import com.sdos.supermarket.data.room.SupermarketDatabase
import org.koin.dsl.module

val repositoryModule = module {

    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    fun provideUserRepository(supermarketDatabase: SupermarketDatabase): UserRepository {
        return UserRepositoryImpl(supermarketDatabase)
    }

    fun provideTaskRepository(supermarketDatabase: SupermarketDatabase): TaskRepository {
        return TaskRepositoryImpl(supermarketDatabase)
    }

    fun provideProductsRepository(productsApiClient: ProductsApiClient, productsCache: ProductsCache): ProductsRepository {
        return ProductsRepositoryImpl(productsApiClient, productsCache)
    }

    single<LoginRepository> { provideLoginRepository() }
    single<UserRepository> { provideUserRepository(get()) }
    single<TaskRepository> { provideTaskRepository(get()) }
    single<ProductsRepository> { provideProductsRepository(get(), get()) }
}