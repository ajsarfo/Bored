package com.sarftec.bored.data.injection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sarftec.bored.data.network.service.TaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object Concrete {

    @ExperimentalSerializationApi
    @Provides
    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/activity/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    @Provides
    fun taskService(retrofit: Retrofit) : TaskService {
        return TaskService.create(retrofit)
    }
}