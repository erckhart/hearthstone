package com.voidx.hearthstone.util

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException


class WaitFor(val millis: Long): ViewAction {
    override fun getDescription(): String {
        return "waiting $millis millis."
    }

    override fun getConstraints(): Matcher<View> {
        return isRoot()
    }

    override fun perform(uiController: UiController?, view: View?) {
        uiController!!.loopMainThreadUntilIdle()
        val startTime = System.currentTimeMillis()
        val endTime = startTime + millis

        while (System.currentTimeMillis() < endTime){}

        throw PerformException.Builder()
            .withActionDescription(this.description)
            .withViewDescription(HumanReadables.describe(view))
            .withCause(TimeoutException())
            .build()
    }

}