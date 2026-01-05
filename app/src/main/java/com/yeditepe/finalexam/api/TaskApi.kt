package com.yeditepe.finalexam.api

import com.yeditepe.finalexam.model.TaskDto
import retrofit2.http.GET

interface TaskApi {

    @GET("tasks")
    suspend fun getTasks(): List<TaskDto>
}
