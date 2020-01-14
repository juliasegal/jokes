package com.julia.apd.chuckie.remote

import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel

import retrofit2.http.*

interface ChuckNorrisApi {

    @GET("jokes/random/")
    suspend fun getRandomJoke(): JokeResponseModel

    @GET("jokes/random/")
    suspend fun getRandomJoke(@Query("firstName") firstName: String, @Query("lastName") lastName: String): JokeResponseModel

    @GET("jokes/random/{numberOfJokes}")
    suspend fun getRandomJokes(@Path("numberOfJokes") numberOfJokes: Int): JokesResponseModel

    companion object {
        const val url = "https://api.icndb.com/"
    }

}