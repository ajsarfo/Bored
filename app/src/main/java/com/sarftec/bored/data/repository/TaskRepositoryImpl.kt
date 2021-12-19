package com.sarftec.bored.data.repository

import com.sarftec.bored.data.network.TaskApi
import com.sarftec.bored.domain.model.Task
import com.sarftec.bored.domain.repository.TaskRepository
import com.sarftec.bored.tools.extra.NetworkManager
import com.sarftec.bored.tools.extra.Resource
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskApi: TaskApi,
    private val networkManager: NetworkManager
) : TaskRepository {

    override suspend fun getRandomTask(): Resource<Task> {
        return if (!networkManager.isNetworkAvailable()) Resource.error("Network Error")
        else taskApi.getRandomTask()
    }
}