package com.julia.apd.chuckie.ui.namejoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.models.JokeModel
import com.julia.apd.chuckie.networking.Resource
import kotlinx.coroutines.launch

class NameJokeViewModel(private val chuckNorrisRepository: ChuckNorrisRepository) : ViewModel() {
    private val _joke = MutableLiveData<Resource<JokeModel>>()

    val joke: LiveData<Resource<JokeModel>> = _joke

    fun getJoke(firstName: String, lastName: String) {
        _joke.value = Resource.loading()
        viewModelScope.launch {
            _joke.value = chuckNorrisRepository.getRandomJoke(firstName, lastName).value
        }
    }
}