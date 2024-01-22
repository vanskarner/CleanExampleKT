package com.vanskarner.cleanexamplekt.util

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.vanskarner.cleanexamplekt.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest

/**
 * Wait until the view is visible until the indicated time
 * @param testScope scope to work within `runTest`
 * @param verificationIntervalInMillis time interval that every so often rechecks that the view
 * is visible
 * @param timeoutMillis Maximum time, in milliseconds, to wait for the view to become visible.
 * @throws AssertionError If the view did not become visible before time ran out.
 * @author Luis H. Olazo
 * @sample example
 */
@ExperimentalCoroutinesApi
internal fun ViewInteraction.waitForDisplayed(
    testScope: TestScope,
    verificationIntervalInMillis: Long = 100,
    timeoutMillis: Long = 5000
) {
    val endTime = System.currentTimeMillis() + timeoutMillis
    do {
        try {
            this.check(matches(isDisplayed()))
            return
        } catch (ignored: Throwable) {
            try {
                testScope.advanceTimeBy(verificationIntervalInMillis)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    } while (System.currentTimeMillis() < endTime)
    throw AssertionError("The view did not become visible before time ran out.")
}

/**
 * Example of using the function [waitForDisplayed]
 */
@ExperimentalCoroutinesApi
private fun example() = runTest {
    //Pressing this button calls an asynchronous operation
    onView(ViewMatchers.withId(R.id.btnFind)).perform(ViewActions.click())
    //Then we wait for the view to be displayed, and for the other synchronous processes
    // it will be called normally
    onView(ViewMatchers.withText(R.string.label_error)).waitForDisplayed(this)
    onView(ViewMatchers.withText(R.string.msg_user_not_found)).check(matches(isDisplayed()))
}
