package com.voidx.hearthstone.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.voidx.hearthstone.R
import com.voidx.hearthstone.feature.list.ListGameOptionsFragment
import com.voidx.hearthstone.util.WaitFor
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit

class ListGameOptionsRobot(private val mockWebServer: MockWebServer) {

    fun start() = apply {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.navigation)

        // Create a graphical FragmentScenario for the TitleScreen
        val scenario = launchFragmentInContainer<ListGameOptionsFragment>(themeResId = R.style.AppTheme)

        // Set the NavController property on the fragment
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
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