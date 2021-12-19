package com.sarftec.bored.domain.repository

import com.sarftec.bored.domain.model.Task
import com.sarftec.bored.tools.extra.Resource

interface TaskRepository {
    suspend fun getRandomTask() : Resource<Task>
}