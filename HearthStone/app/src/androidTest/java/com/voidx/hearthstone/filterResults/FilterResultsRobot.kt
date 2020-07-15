package com.voidx.hearthstone.filterResults

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.voidx.hearthstone.R
import com.voidx.hearthstone.feature.filterResult.FilterResultFragment
import com.voidx.hearthstone.feature.list.ListGameOptionsFragmentDirections
import com.voidx.hearthstone.util.startNavigation
import okhttp3.mockwebserver.MockWebServer

class FilterResultsRobot(private val mockWebServer: MockWebServer) {

    fun start() = apply {
        val directions = ListGameOptionsFragmentDirections.actionListToResults("Classes", "Death Knight")
        startNavigation<FilterResultFragment>(directions.arguments)
    }

    fun mockSuccessResponse() = apply {
        mockWebServer.dispatcher = FilterResultsMockFactory.SuccessDispatcher
    }

    fun checkResults() = apply {
        onView(withId(R.id.category))
            .check(matches(isDisplayed()))
            .check(matches(withText("Classes")))

        onView(withId(R.id.list)).check(matches(isDisplayed()))

        onView(withContentDescription("Anduin Wrynn")).check(matches(isDisplayed()))

    }

}