package com.julia.apd.chuckie.ui.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.models.JokeModel
import kotlinx.coroutines.CoroutineScope


class PaintingModelSourceFactory(
    private val chuckNorrisRepository: ChuckNorrisRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, JokeModel>() {
    private val paintingLiveDataSource = MutableLiveData<PaintingsDataSource>()

    override fun create(): DataSource<Int, JokeModel> {
        val paintingDataSource = PaintingsDataSource(chuckNorrisRepository, scope)
        paintingLiveDataSource.postValue(paintingDataSource)
        return paintingDataSource
    }

    fun getDataSourceLiveData(): LiveData<PaintingsDataSource> {
        return paintingLiveDataSource
    }
}
