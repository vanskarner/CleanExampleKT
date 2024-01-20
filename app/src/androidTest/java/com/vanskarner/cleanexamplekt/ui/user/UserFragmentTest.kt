package com.vanskarner.cleanexamplekt.ui.user

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.vanskarner.cleanexamplekt.R
import com.vanskarner.cleanexamplekt.ui.MainActivity
import com.vanskarner.cleanexamplekt.util.CustomRecyclerMatcher.Companion.withRecyclerViewItemCount
import com.vanskarner.cleanexamplekt.util.DataBindingIdlingResource
import com.vanskarner.user.UserComponent
import com.vanskarner.user.businesslogic.UserData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@ExperimentalCoroutinesApi
class UserFragmentTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userComponent: UserComponent

    private lateinit var dataBindingIdlingResource: IdlingResource

    @Before
    fun registerIdlingResources() {
        hiltRule.inject()
        dataBindingIdlingResource = DataBindingIdlingResource(activityScenarioRule)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun pressSave_withValidUser_showAddedUserMsg() {
        onView(withId(R.id.edtName))
            .perform(typeText("Luis"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.edtAge))
            .perform(typeText("32"))
        onView(withId(R.id.btnSave)).perform(click())
        Thread.sleep(2000)
        onView(withText(R.string.label_info)).check(matches(isDisplayed()))
        onView(withText(R.string.msg_user_added)).check(matches(isDisplayed()))
    }

    @Test
    fun pressSave_withInvalidUser_showErrorMsg() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val errorMsg = buildString {
            appendLine(appContext.getString(R.string.msg_invalid_name))
            appendLine(appContext.getString(R.string.msg_invalid_age))
        }

        onView(withId(R.id.btnSave)).perform(click())
        Thread.sleep(2000)
        onView(withText(R.string.label_error)).check(matches(isDisplayed()))
        onView(withId(R.id.txtErrorMsg)).check(matches(withText(errorMsg)))
    }

    @Test
    fun pressList_withRegisteredUsers_showList() = runTest {
        val expectedNumberItems = 2
        userComponent.save(UserData(name = "Luis", age = 32))
        userComponent.save(UserData(name = "Juan", age = 33))
        onView(withId(R.id.btnList)).perform(click())
        Thread.sleep(2000)

        onView(withText(R.string.label_users)).check(matches(isDisplayed()))
        onView(withId(R.id.rcvItems)).check(matches(withRecyclerViewItemCount(expectedNumberItems)))
    }

    @Test
    fun pressList_noRegisteredUsers_showEmptyList() {
        onView(withId(R.id.btnList)).perform(click())
        Thread.sleep(2000)

        onView(withText(R.string.label_users)).check(matches(isDisplayed()))
        onView(withId(R.id.rcvItems)).check(matches(withRecyclerViewItemCount(0)))
    }

    @Test
    fun pressSearch_withValidId_showUser() = runTest {
        val userId = 1
        val userExample = UserData(name = "Luis", age = 32)
        userComponent.save(userExample)

        onView(withId(R.id.edtIdUser))
            .perform(typeText("$userId"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.btnFind)).perform(click())
        Thread.sleep(3000)
        onView(withText(R.string.label_user)).check(matches(isDisplayed()))
        onView(withId(R.id.txtId)).check(matches(withText("$userId")))
        onView(withId(R.id.txtName)).check(matches(withText(userExample.name)))
        onView(withId(R.id.txtAge)).check(matches(withText("${userExample.age}")))
    }

    @Test
    fun pressSearch_withInvalidId_showErrorMsg() {
        onView(withId(R.id.btnFind)).perform(click())
        Thread.sleep(2000)

        onView(withText(R.string.label_error)).check(matches(isDisplayed()))
        onView(withText(R.string.msg_user_not_found)).check(matches(isDisplayed()))
    }


}