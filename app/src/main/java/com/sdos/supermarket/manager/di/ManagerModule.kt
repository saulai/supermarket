package com.sdos.supermarket.manager.di

import com.sdos.supermarket.domain.interactor.AddTasksInteractor
import com.sdos.supermarket.domain.interactor.BaseTasksInteractor
import com.sdos.supermarket.manager.ManagerViewModel
import com.sdos.supermarket.manager.ManagerViewModelFactory
import org.koin.dsl.module


val managerModule = module {

    fun provideManagerViewModel(
        baseTasksInteractor: BaseTasksInteractor,
        addTasksInteractor: AddTasksInteractor
    ) =
        ManagerViewModel(baseTasksInteractor, addTasksInteractor)

    fun provideViewModelFactory(managerViewModel: ManagerViewModel): ManagerViewModelFactory =
        ManagerViewModelFactory(managerViewModel)

    factory<ManagerViewModel> { provideManagerViewModel(get(), get()) }
    factory<ManagerViewModelFactory> { provideViewModelFactory(get()) }
}