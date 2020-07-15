package com.voidx.hearthstone.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.voidx.hearthstone.R
import com.voidx.hearthstone.feature.list.ListGameOptionsFragment
import com.voidx.hearthstone.util.startNavigation
import okhttp3.mockwebserver.MockWebServer

class ListGameOptionsRobot(private val mockWebServer: MockWebServer) {

    fun start() = apply {
        startNavigation<ListGameOptionsFragment>()
    }

    fun checkTitle() = apply {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
        onView(withText(R.string.app_name)).check(matches(withText("HearthStone")))
    }

    fun mockSuccessfulResponse() = apply {
        mockWebServer.dispatcher = ListGameOptionsMockFactory.SuccessDispatcher
    }

    fun checkListing() = apply {
        onView(withText("Classes")).check(matches(isDisplayed()))
    }

}