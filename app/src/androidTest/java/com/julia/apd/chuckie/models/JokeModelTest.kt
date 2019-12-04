package com.julia.apd.chuckie.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokeValueResponseModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JokeModelTest {

    @Test
    fun map() {
        val joke = "not funny joke"
        val value = JokeValueResponseModel(1, joke)
        val jm = JokeModel.map(JokeResponseModel("a", value))
        assertEquals(jm.id, 1)
        assertEquals(jm.joke, joke)
    }

    @Test
    fun mapHtml() {
        val joke = "\" not funny joke \""
        val jokeHtml = "&quot not funny joke &quot"
        val value = JokeValueResponseModel(99, jokeHtml)
        val jm = JokeModel.map(JokeResponseModel("a", value))
        assertEquals(jm.id, 99)
        assertEquals(jm.joke, joke)
    }
}