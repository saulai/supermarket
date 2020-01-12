package com.sdos.supermarket.technician.di

import com.sdos.supermarket.domain.interactor.GetUserInteractor
import com.sdos.supermarket.domain.interactor.GetUserTasksInteractor
import com.sdos.supermarket.domain.interactor.SetCompletedTasksInteractor
import com.sdos.supermarket.technician.TechnicianViewModel
import com.sdos.supermarket.technician.TechnicianViewModelFactory
import org.koin.dsl.module


val technicianModule = module {

    fun provideTechnicianViewModel(
        getUserTasksInteractor: GetUserTasksInteractor,
        userInteractor: GetUserInteractor,
        setCompletedTasksInteractor: SetCompletedTasksInteractor
    ) =
        TechnicianViewModel(getUserTasksInteractor, userInteractor, setCompletedTasksInteractor)

    fun provideViewModelFactory(technicianViewModel: TechnicianViewModel): TechnicianViewModelFactory =
        TechnicianViewModelFactory(technicianViewModel)

    factory<TechnicianViewModel> { provideTechnicianViewModel(get(), get(), get()) }
    factory<TechnicianViewModelFactory> { provideViewModelFactory(get()) }
}