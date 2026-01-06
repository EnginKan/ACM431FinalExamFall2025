package com.yeditepe.finalexam

import com.yeditepe.finalexam.api.TaskApi
import com.yeditepe.finalexam.repository.TaskRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class TaskRepositoryTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: TaskApi
    private lateinit var repository: TaskRepository

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApi::class.java)

        repository = TaskRepository(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchTasksreturnsmappedTasklist()  {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                    [
                      { "id": 1, "title": "Buy milk", "completed": false },
                      { "id": 2, "title": "Write exam", "completed": true }
                    ]
                    """.trimIndent()
                )
        )

        val result = repository.fetchTasks()

        //assertTrue(result.isSuccess)

        assertEquals(2, result.size)
        assertEquals("Buy milk", result.get(0).title)


    }
}
