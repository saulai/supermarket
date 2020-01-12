package com.sdos.supermarket.common.di

import android.app.Application
import androidx.room.Room
import com.sdos.supermarket.data.api.ProductsApiClient
import com.sdos.supermarket.data.room.SUPERMARKET_DATABASE
import com.sdos.supermarket.data.room.SupermarketDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): SupermarketDatabase {
        return Room.databaseBuilder(
            application,
            SupermarketDatabase::class.java,
            SUPERMARKET_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<SupermarketDatabase> { provideDatabase(androidApplication()) }
}


val netModule = module {

    fun provideProducstApiClient() = ProductsApiClient()

    single<ProductsApiClient> { provideProducstApiClient() }

}
