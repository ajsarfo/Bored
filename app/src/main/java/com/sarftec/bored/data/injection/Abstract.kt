package com.sarftec.bored.data.injection

import com.sarftec.bored.data.repository.TaskRepositoryImpl
import com.sarftec.bored.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Abstract {

    @Binds
    fun taskRepository(repository: TaskRepositoryImpl) : TaskRepository
}