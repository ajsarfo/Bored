package com.sarftec.bored.data.network.mapper

import com.sarftec.bored.data.network.model.Task
import javax.inject.Inject

class TaskMapper @Inject constructor() {
    fun toDomainTask(task: Task) : com.sarftec.bored.domain.model.Task {
        return com.sarftec.bored.domain.model.Task(
            task.activity,
            task.link,
            task.type,
            task.participants,
            task.price
        )
    }
}