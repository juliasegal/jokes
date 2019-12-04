package com.julia.apd.chuckie.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flydiem.demo.testtools.MainCoroutineRule
import com.julia.apd.chuckie.mocks.FakeJokeApi
import com.julia.apd.chuckie.networking.Resource
import com.julia.apd.chuckie.networking.ResponseHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class ChuckNorrisImplTest {

    private lateinit var chuckNorrisRepository: ChuckNorrisRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var jokeServiceApi = FakeJokeApi()

    @Before
    fun setUp() {
        chuckNorrisRepository = ChuckNorrisImpl(jokeServiceApi, ResponseHandler())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJokeRepository() = mainCoroutineRule.runBlockingTest {
        assertTrue(chuckNorrisRepository.getRandomJoke().value is Resource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJokeSuccess() = mainCoroutineRule.runBlockingTest {
        assertEquals(chuckNorrisRepository.getRandomJoke().value!!.status, Status.SUCCESS)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJokeFail() = mainCoroutineRule.runBlockingTest {
        jokeServiceApi.isFail = true
        assertEquals(chuckNorrisRepository.getRandomJoke().value!!.status, Status.ERROR)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJoke() = mainCoroutineRule.runBlockingTest {
        val value = chuckNorrisRepository.getRandomJoke().value
        assertEquals(value!!.data!!.joke, "jokey number 1")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomNamedJoke() = mainCoroutineRule.runBlockingTest {
        val firstName = "Ed"
        val lastName = "Balls"
        val value = chuckNorrisRepository.getRandomJoke(firstName, lastName).value
        assertTrue(value!!.data!!.joke.contains(firstName))
        assertTrue(value!!.data!!.joke.contains(lastName))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJokes() = mainCoroutineRule.runBlockingTest {
        val numberOfJokes = 20
        val value = chuckNorrisRepository.getRandomJokes(numberOfJokes)
        assertEquals(value.status, Status.SUCCESS)
        assertEquals(value.data!!.size, numberOfJokes)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomJokesError() = mainCoroutineRule.runBlockingTest {
        jokeServiceApi.isFail = true
        val numberOfJokes = 20
        val value = chuckNorrisRepository.getRandomJokes(numberOfJokes)
        assertEquals(value.status, Status.ERROR)
        assertEquals(value.data, null)
    }
}