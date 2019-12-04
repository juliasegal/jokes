package com.julia.apd.chuckie.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.julia.apd.chuckie.models.JokeModel
import com.julia.apd.chuckie.remote.ChuckNorrisApi
import com.julia.apd.chuckie.networking.Resource
import com.julia.apd.chuckie.networking.ResponseHandler

interface ChuckNorrisRepository {
    suspend fun getRandomJoke(): LiveData<Resource<JokeModel>>
}

class ChuckNorrisImpl(private val jokeServiceApi: ChuckNorrisApi, private val responseHandler: ResponseHandler) : ChuckNorrisRepository {

    override suspend fun getRandomJoke(): LiveData<Resource<JokeModel>> {
        val jokeData = MutableLiveData<Resource<JokeModel>>()
        try {
            val response = jokeServiceApi.getRandomJoke()
            jokeData.value = responseHandler.handleSuccess(JokeModel.map(response))
        } catch (ex: Exception) {
            jokeData.value = responseHandler.handleException(ex)
        }
        return jokeData
    }
}
