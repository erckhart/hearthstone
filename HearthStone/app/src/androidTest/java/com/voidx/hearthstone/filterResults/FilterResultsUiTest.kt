package com.voidx.hearthstone.filterResults

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.voidx.hearthstone.util.start8080
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilterResultsUiTest {

    private val mockWebServer = MockWebServer().apply { start8080() }

    private lateinit var robot: FilterResultsRobot

    @Before
    fun setUp() {
        robot = FilterResultsRobot(mockWebServer)
    }

    @Test
    fun testShowSuccessfullyFilterResults() {
        robot
            .mockSuccessResponse()
            .start()
            .checkResults()
    }

}