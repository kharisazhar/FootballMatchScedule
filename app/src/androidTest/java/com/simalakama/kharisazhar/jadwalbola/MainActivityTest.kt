package com.simalakama.kharisazhar.jadwalbola

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.simalakama.kharisazhar.jadwalbola.R.id.*
import com.simalakama.kharisazhar.jadwalbola.activity.MainActivity
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    // Register your Idling Resource before any tests regarding this component
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testAppBehaviour() {
        delay(6)

        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(team_nav_bottom))
            .check(matches(isDisplayed()))

        onView(withId(team_nav_bottom))
            .perform(click())

        delay(3)

        onView(withId(list_team))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        onView(withId(add_to_favorite))
            .perform(click())
        onView(ViewMatchers.withText("Added to favorite"))
            .check(matches(isDisplayed()))
    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}