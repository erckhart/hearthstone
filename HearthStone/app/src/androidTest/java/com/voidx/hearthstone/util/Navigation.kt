package com.voidx.hearthstone.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.voidx.hearthstone.R

inline fun <reified F: Fragment> startNavigation(arguments: Bundle? = null): NavController {
    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    navController.setGraph(R.navigation.navigation)

    val scenario = launchFragmentInContainer<F>(arguments, R.style.AppTheme)

    scenario.onFragment { fragment ->
        Navigation.setViewNavController(fragment.requireView(), navController)
    }

    return navController
}