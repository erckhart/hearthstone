package com.voidx.hearthstone.list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.voidx.hearthstone.util.start8080
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListGameOptionUiTest {

    private val mockWebServer = MockWebServer().apply { start8080() }

    private lateinit var robot: ListGameOptionsRobot

    @Before
    fun setUp() {
        robot = ListGameOptionsRobot(mockWebServer)
    }

    @Test
    fun testSuccessfullyShowGameOptions() {
        robot
            .mockSuccessfulResponse()
            .start()
            .checkTitle()
            .checkListing()
    }
}