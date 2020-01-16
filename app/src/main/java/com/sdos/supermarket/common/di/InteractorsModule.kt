package com.sdos.supermarket.common.di

import com.sdos.supermarket.domain.repository.LoginRepository
import com.sdos.supermarket.domain.repository.ProductsRepository
import com.sdos.supermarket.domain.repository.TaskRepository
import com.sdos.supermarket.domain.repository.UserRepository
import com.sdos.supermarket.domain.interactor.*
import org.koin.dsl.module

val interactorModule = module {

    fun provideLoginInteractor(loginRepository: LoginRepository, userRepository: UserRepository) =
        LoginInteractor(loginRepository, userRepository)

    fun provideCreateDataBaseInteractor(
        userRepository: UserRepository,
        taskRepository: TaskRepository
    ) =
        CreateDataBaseInteractor(userRepository, taskRepository)

    fun provideBaseTaskInteractor(taskRepository: TaskRepository) =
        BaseTasksInteractor(taskRepository)

    fun provideAddTaskInteractor(taskRepository: TaskRepository, userRepository: UserRepository) =
        AddTasksInteractor(taskRepository, userRepository)

    fun providePeachesInteractor(productsRepository: ProductsRepository) =
        PeachesInteractor(productsRepository)

    fun provideGetUserTasksInteractor(taskRepository: TaskRepository) =
        GetUserTasksInteractor(taskRepository)

    fun provideGetUserInteractor(userRepository: UserRepository) =
        GetUserInteractor(userRepository)

    fun provideSetCompletedTaskInteractor(taskRepository: TaskRepository) =
        SetCompletedTasksInteractor(taskRepository)

    single<LoginInteractor> { provideLoginInteractor(get(), get()) }
    single<CreateDataBaseInteractor> { provideCreateDataBaseInteractor(get(), get()) }
    single<BaseTasksInteractor> { provideBaseTaskInteractor(get()) }
    single<AddTasksInteractor> { provideAddTaskInteractor(get(), get()) }
    single<PeachesInteractor> { providePeachesInteractor(get()) }
    single<GetUserTasksInteractor> { provideGetUserTasksInteractor(get()) }
    single<GetUserInteractor> { provideGetUserInteractor(get()) }
    single<SetCompletedTasksInteractor> { provideSetCompletedTaskInteractor(get()) }
}