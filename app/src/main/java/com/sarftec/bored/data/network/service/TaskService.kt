package com.sarftec.bored.data.network.service

import com.sarftec.bored.data.network.model.Task
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface TaskService {

    @GET(".")
    suspend fun getRandomTask() : Response<Task>

    companion object {
        fun create(retrofit: Retrofit) : TaskService {
            return retrofit.create(TaskService::class.java)
        }
    }
}