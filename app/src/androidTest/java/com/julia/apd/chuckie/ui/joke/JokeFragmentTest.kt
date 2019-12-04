package com.julia.apd.chuckie.ui.joke

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.julia.apd.chuckie.MainActivity
import com.julia.apd.chuckie.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JokeFragmentTest{

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        Espresso.onView(ViewMatchers.withId(R.id.navigation_joke)).perform(ViewActions.click())
    }

    @Test
    fun getJoke() {
        Espresso.onView(ViewMatchers.withId(R.id.random_joke)).perform(ViewActions.click())
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withText("Um..."))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun getNamedJoke() {
        Espresso.onView(ViewMatchers.withId(R.id.named_joke)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.name_joke_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}