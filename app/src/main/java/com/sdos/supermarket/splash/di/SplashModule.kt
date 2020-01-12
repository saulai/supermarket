package com.sdos.supermarket.splash.di

import com.sdos.supermarket.domain.interactor.CreateDataBaseInteractor
import com.sdos.supermarket.splash.SplashViewModel
import com.sdos.supermarket.splash.SplashViewModelFactory
import org.koin.dsl.module


val splashModule = module {

    fun provideSplashViewModel(createDataBaseInteractor: CreateDataBaseInteractor) =
        SplashViewModel(createDataBaseInteractor)

    fun provideViewModelFactory(splashViewModel: SplashViewModel): SplashViewModelFactory =
        SplashViewModelFactory(splashViewModel)

    factory<SplashViewModel> { provideSplashViewModel(get()) }
    factory<SplashViewModelFactory> { provideViewModelFactory(get()) }
}