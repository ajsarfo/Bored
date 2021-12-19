package com.sarftec.bored.data.network

import com.sarftec.bored.data.network.mapper.TaskMapper
import com.sarftec.bored.data.network.service.TaskService
import com.sarftec.bored.domain.model.Task
import com.sarftec.bored.domain.repository.TaskRepository
import com.sarftec.bored.tools.extra.Resource
import javax.inject.Inject

class TaskApi @Inject constructor(
    private val service: TaskService,
    private val taskMapper: TaskMapper
) : TaskRepository {

    override suspend fun getRandomTask(): Resource<Task> {
        return try {
            val response = service.getRandomTask()
            if (!response.isSuccessful) Resource.error("Error => ${response.errorBody()}")
            else Resource.success(taskMapper.toDomainTask(response.body()!!))
        } catch (e: Exception) {
            Resource.error("Error => ${e.message}")
        }
    }
}