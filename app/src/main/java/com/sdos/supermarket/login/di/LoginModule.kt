package com.sdos.supermarket.login.di

import com.sdos.supermarket.domain.interactor.LoginInteractor
import com.sdos.supermarket.login.LoginViewModel
import com.sdos.supermarket.login.LoginViewModelFactory
import org.koin.dsl.module


val loginModule = module {
    fun provideLoginViewModel(loginInteractor: LoginInteractor) = LoginViewModel(loginInteractor)

    fun provideViewModelFactory(viewModel: LoginViewModel): LoginViewModelFactory =
        LoginViewModelFactory(viewModel)

    factory<LoginViewModel> { provideLoginViewModel(get()) }
    factory<LoginViewModelFactory> { provideViewModelFactory(get()) }
}