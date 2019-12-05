package com.julia.apd.chuckie.ui.namejoke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.flydiem.demo.testtools.LiveDataTestUtil
import com.julia.apd.chuckie.dao.ChuckNorrisImpl
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.dao.Status
import com.julia.apd.chuckie.mocks.FakeJokeApi
import com.julia.apd.chuckie.models.JokeModel
import com.julia.apd.chuckie.networking.Resource
import com.julia.apd.chuckie.networking.ResponseHandler
import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokeValueResponseModel
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NameJokeViewModelTest{
    val firstName = "Ed"
    val lastName = "Balls"

    @Mock
    lateinit var observer: Observer<Resource<JokeModel>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var api: FakeJokeApi
    lateinit var viewModel: NameJokeViewModel
    lateinit var repository: ChuckNorrisRepository

    private fun setUp(isFail: Boolean) {
        MockitoAnnotations.initMocks(this)
        api = FakeJokeApi()
        api.isFail = isFail
        repository = ChuckNorrisImpl(api, ResponseHandler())
        viewModel = NameJokeViewModel(repository)
    }

    @Test
    fun getJokesNotCalled() {
        setUp(false)
        val jokeLiveData = LiveDataTestUtil.getValue(viewModel.joke)
        TestCase.assertEquals(jokeLiveData, null)
    }

    @Test
    fun getJokesLoading() {
        val data = Resource.loading(null)
        setUp(false)
        viewModel.joke.observeForever(observer)
        viewModel.getJoke(firstName, lastName)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(data)
    }

    @Test
    fun getJokesSuccess() {
        val joke = "jokey number 2 with Ed Balls"
        val value = JokeValueResponseModel(2, joke)
        val jm = JokeModel.map(JokeResponseModel("success", value))
        val data = Resource.success(jm)

        setUp(false)
        viewModel.joke.observeForever(observer)
        viewModel.getJoke(firstName, lastName)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(Mockito.any())
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(data)
    }

    @Test
    fun getJokesFail() {
        val loading = Resource.loading(null)
        val failure = Resource.error("Failure", null)
        setUp(true)
        api.isFail = true
        viewModel.joke.observeForever(observer)
        viewModel.getJoke(firstName, lastName)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(loading)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(failure)
    }
}