package com.julia.apd.chuckie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBottomViewDisplayed() {
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testClickJokeDisplayed() {
        onView(withId(R.id.navigation_joke)).perform(click())
        onView(withId(R.id.joke_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickJokesDisplayed() {
        onView(withId(R.id.navigation_jokes)).perform(click())
        onView(withId(R.id.jokes_layout)).check(matches(isDisplayed()))
    }

}