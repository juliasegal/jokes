package com.julia.apd.chuckie.remote

import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ChuckNorrisApi {

    @GET("jokes/random/")
    suspend fun getRandomJoke(): JokeResponseModel

    @GET("jokes/random/")
    suspend fun getRandomJoke(@Query("firstName") firstName: String, @Query("lastName") lastName: String): JokeResponseModel

    @GET("jokes/random/{numberOfJokes}")
    suspend fun getRandomJokes(@Path("numberOfJokes") numberOfJokes: Int): JokesResponseModel

    companion object {
        private const val JOKE_SERVER_URL = "https://api.icndb.com/"

        fun create(okHttpClient: OkHttpClient): ChuckNorrisApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(JOKE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(okHttpClient)
                .build()
            return retrofit.create(ChuckNorrisApi::class.java)
        }
    }
}