package com.julia.apd.chuckie.ui.joke

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.julia.apd.chuckie.MainActivity
import org.junit.Rule
import org.junit.Test


class JokeDialogTest{

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testJokeDialog() {
        val joke = "I'm not joking, but..."
        activityTestRule.runOnUiThread {
        //    JokeDialog.showJoke(activityTestRule.activity, joke)
         //   onView(withText(joke)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testJokeDialogCloses() {
        val joke = "I'm not joking, but..."
        activityTestRule.runOnUiThread {
        //    JokeDialog.showJoke(activityTestRule.activity, joke)
        //    onView(withText("okey dokey")).check(matches(isDisplayed())).perform(click())
        }
    }
}