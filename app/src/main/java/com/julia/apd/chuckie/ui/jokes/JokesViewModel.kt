package com.julia.apd.chuckie.ui.jokes

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.models.JokeModel

class JokesViewModel(private val chuckNorrisRepository: ChuckNorrisRepository) : ViewModel() {
    private var jokesSourceFactory:  PaintingModelSourceFactory? = null
    var pagedJokes: LiveData<PagedList<JokeModel>>

    init {
        jokesSourceFactory = PaintingModelSourceFactory(chuckNorrisRepository, viewModelScope)
        val config = PagedList.Config.Builder()
            .setPageSize(PaintingsDataSource.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        pagedJokes = LivePagedListBuilder(jokesSourceFactory!!, config).build()
    }

    fun getError(): LiveData<String>? {
        return Transformations.switchMap(jokesSourceFactory?.getDataSourceLiveData()!!) {
            it.getLiveErrors()
        }
    }

    fun getLoading(): LiveData<Boolean>? {
        return Transformations.switchMap(jokesSourceFactory?.getDataSourceLiveData()!!) {
            it.getLiveLoading()
        }
    }
}