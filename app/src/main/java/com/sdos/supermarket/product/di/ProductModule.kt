package com.sdos.supermarket.product.di

import com.sdos.supermarket.domain.interactor.PeachesInteractor
import com.sdos.supermarket.product.ProductViewModel
import com.sdos.supermarket.product.ProductViewModelFactory
import org.koin.dsl.module


val productModule = module {

    fun provideProductViewModel(peachesInteractor: PeachesInteractor) =
        ProductViewModel(peachesInteractor)

    fun provideViewModelFactory(productViewModel: ProductViewModel): ProductViewModelFactory =
        ProductViewModelFactory(productViewModel)

    factory<ProductViewModel> { provideProductViewModel(get()) }
    factory<ProductViewModelFactory> { provideViewModelFactory(get()) }
}