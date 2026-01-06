package com.yeditepe.finalexam.api

import com.yeditepe.finalexam.model.TaskDto
import kotlinx.coroutines.delay

class FakeTaskApi : TaskApi {

    override suspend fun getTasks(): List<TaskDto> {
        delay(500)
        return listOf(
            TaskDto(1, "Prepare Presentation", false),
            TaskDto(2, "Submit Assignment", true),
            TaskDto(3, "Study for Final Exam", false)
        )
    }
}
