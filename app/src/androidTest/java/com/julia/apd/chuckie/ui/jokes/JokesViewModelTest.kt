package com.julia.apd.chuckie.ui.jokes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.flydiem.demo.testtools.LiveDataTestUtil
import com.julia.apd.chuckie.dao.ChuckNorrisImpl
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.mocks.FakeJokeApi
import com.julia.apd.chuckie.models.JokeModel
import com.julia.apd.chuckie.networking.ResponseHandler
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class JokesViewModelTest {
    @Mock
    lateinit var observer: Observer<PagedList<JokeModel>>

    @Mock
    lateinit var errorObserver: Observer<String>

    @Mock
    lateinit var progressObserver: Observer<Boolean>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var api: FakeJokeApi
    lateinit var viewModel: JokesViewModel
    lateinit var repository: ChuckNorrisRepository

    private fun setUp(isFail: Boolean) {
        MockitoAnnotations.initMocks(this)
        api = FakeJokeApi()
        api.isFail = isFail
        repository = ChuckNorrisImpl(api, ResponseHandler())
        viewModel = JokesViewModel(repository)
    }

    @Test
    fun getJokesNotCalled() {
        setUp(false)
        val paintingLiveData = LiveDataTestUtil.getValue(viewModel.pagedJokes)
        assertTrue(paintingLiveData.size == 0)
    }

    @Test
    fun getJokesLoading() {
        setUp(false)
        viewModel.pagedJokes.observeForever(observer)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(Mockito.any())
    }

    @Test
    fun getJokesSuccess() {
        setUp(false)
        viewModel.pagedJokes.observeForever(observer)
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(Mockito.any())
        Mockito.verify(observer, Mockito.timeout(5000)).onChanged(Mockito.any())
    }

    @Test
    fun getJokesFail() {
        setUp(true)
        api.isFail = true
        viewModel.getError()!!.observeForever(errorObserver)
        viewModel.pagedJokes.observeForever(observer)
        Mockito.verify(errorObserver, Mockito.timeout(5000)).onChanged("Failure")
    }

    @Test
    fun getJokesProgress() {
        setUp(false)
        viewModel.getLoading()!!.observeForever(progressObserver)
        viewModel.pagedJokes.observeForever(observer)
        Mockito.verify(progressObserver, Mockito.timeout(5000)).onChanged(true)
        Mockito.verify(progressObserver, Mockito.timeout(5000)).onChanged(false)
    }
}