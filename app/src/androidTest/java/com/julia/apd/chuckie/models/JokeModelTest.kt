package com.julia.apd.chuckie.models

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokeValueResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JokeModelTest {

    @Test
    fun jokeMap() {
        val joke = "not funny joke"
        val value = JokeValueResponseModel(1, joke)
        val jm = JokeModel.map(JokeResponseModel("a", value))
        assertEquals(jm.id, 1)
        assertEquals(jm.joke, joke)
    }

    @Test
    fun jokeMapHtml() {
        val joke = "\" not funny joke \""
        val jokeHtml = "&quot not funny joke &quot"
        val value = JokeValueResponseModel(99, jokeHtml)
        val jm = JokeModel.map(JokeResponseModel("a", value))
        assertEquals(jm.id, 99)
        assertEquals(jm.joke, joke)
    }

    @Test
    fun jokesMap() {
        val howMany = 100
        val jokeList = getJokeList(howMany)
        val jokes = JokesModel.map(jokeList)
        assertEquals(jokes.size, howMany)
        assertEquals(jokes[0].joke, "jokey number 1")
    }

    private fun getJokeList(numberOfJokes: Int): JokesResponseModel {
        val jokeList = mutableListOf<JokeValueResponseModel>()
        if (numberOfJokes > 1) {
            for (i in 1..numberOfJokes) {
                val jokeStr = "jokey number $i"
                val joke = JokeValueResponseModel(i, jokeStr)
                jokeList.add(joke)
            }
        }
        return JokesResponseModel("success", jokeList.toList())
    }
}