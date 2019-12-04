package com.julia.apd.chuckie.ui.jokes

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.julia.apd.chuckie.MainActivity
import com.julia.apd.chuckie.R
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JokesFragmentTest{
    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        onView(withId(R.id.navigation_jokes)).perform(click())
    }

    @Test
    fun getJokesList() {
        onView(withId(R.id.jokes_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun getJokesListData() {
        Thread.sleep(2000)
        val recyclerView = activityTestRule.activity.findViewById(R.id.jokes_list) as RecyclerView
        val itemCount = recyclerView.adapter!!.itemCount
        assertEquals(12*2, itemCount)
    }

    @Test
    fun getJokesListDataMore() {
        Thread.sleep(2000)
        val recyclerView = activityTestRule.activity.findViewById(R.id.jokes_list) as RecyclerView
        val itemCount = recyclerView.adapter!!.itemCount
        assertEquals(12*2, itemCount)

       // onView(withId(R.id.jokes_list)).perform(RecyclerViewActions.scrollToPosition<JokeViewHolder>(60))
       // Thread.sleep(2000)
       // assertEquals(12*3, itemCount)
    }
}