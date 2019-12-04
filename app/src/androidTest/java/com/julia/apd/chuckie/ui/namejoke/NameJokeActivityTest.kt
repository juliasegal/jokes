package com.julia.apd.chuckie.ui.namejoke

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.julia.apd.chuckie.R
import org.junit.Rule
import org.junit.Test

class NameJokeActivityTest{
    @get:Rule
    val activityTestRule = ActivityTestRule(NameJokeActivity::class.java)

    @Test
    fun getNamedJokeNoInput() {
        onView(withId(R.id.name_done_button)).perform(ViewActions.click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.name_error_first_last)))
    }

    @Test
    fun getNamedJokeBadInput() {
        onView(withId(R.id.name_entry)).perform(typeText("A bad input"))
        onView(withId(R.id.name_done_button)).perform(ViewActions.click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.name_error_first_last)))
    }

    @Test
    fun getNamedJoke() {
        onView(withId(R.id.name_entry)).perform(typeText("Twinkly Star"))
        onView(withId(R.id.name_done_button)).perform(ViewActions.click())
        Thread.sleep(3000)
        onView(withText("Um...")).check(matches(ViewMatchers.isDisplayed()))
    }
}