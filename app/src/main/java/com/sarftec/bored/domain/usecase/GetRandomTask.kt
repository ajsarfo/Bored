package com.sarftec.bored.domain.usecase

import com.sarftec.bored.domain.model.Task
import com.sarftec.bored.domain.repository.TaskRepository
import com.sarftec.bored.tools.extra.Resource
import javax.inject.Inject

class GetRandomTask @Inject constructor(
    private val repository: TaskRepository
) : UseCase<UseCase.EmptyParam, GetRandomTask.TaskResult>() {

    override suspend fun execute(param: EmptyParam?): TaskResult {
        return TaskResult(
            repository.getRandomTask()
        )
    }

    class TaskResult(val result: Resource<Task>) : Response
}