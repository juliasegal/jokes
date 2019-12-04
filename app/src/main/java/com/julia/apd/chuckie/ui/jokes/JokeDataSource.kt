package com.julia.apd.chuckie.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.dao.Status
import com.julia.apd.chuckie.models.JokeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class PaintingsDataSource(
    private val chuckNorrisRepository: ChuckNorrisRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, JokeModel>() {
    private val errorLiveData = MutableLiveData<String>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun getLiveErrors(): LiveData<String> {
        return errorLiveData
    }

    fun getLiveLoading(): LiveData<Boolean> {
        return loadingLiveData
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, JokeModel>
    ) {
        scope.launch {
            val result = chuckNorrisRepository.getRandomJokes(PAGE_SIZE)
            if (result.status == Status.SUCCESS) {
                callback.onResult(result.data!!, null, FIRST_PAGE + 1)
            } else {
                errorLiveData.value = result.message
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, JokeModel>) {
        scope.launch {
            val result = chuckNorrisRepository.getRandomJokes(PAGE_SIZE)
            val key = if (params.key > 1) params.key - 1 else 0
            if (result.status == Status.SUCCESS) {
                callback.onResult(result.data!!, key)
            } else {
                errorLiveData.value = result.message
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, JokeModel>) {
        scope.launch {
            val result = chuckNorrisRepository.getRandomJokes(PAGE_SIZE)
            if (result.status == Status.SUCCESS) {
                callback.onResult(result.data!!, params.key + 1)
            } else {
                errorLiveData.value = result.message
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 12
        const val FIRST_PAGE = 1
    }
}