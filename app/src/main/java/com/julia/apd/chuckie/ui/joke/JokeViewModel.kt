package com.julia.apd.chuckie.ui.joke

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.models.JokeModel
import com.julia.apd.chuckie.networking.Resource
import kotlinx.coroutines.launch

class JokeViewModel(private val chuckNorrisRepository: ChuckNorrisRepository) : ViewModel() {
    val THREE_SECONDS: Long = 3000
    private val _joke = MutableLiveData<Resource<JokeModel>>()

    val joke: LiveData<Resource<JokeModel>> = _joke

    fun getJoke() {
        _joke.value = Resource.loading()
        viewModelScope.launch {
            val data = chuckNorrisRepository.getRandomJoke().value
            // wait 3 seconds...because as specified
            Handler().postDelayed({ _joke.value = data }, THREE_SECONDS)

        }
    }
}