package com.julia.apd.chuckie.mocks

import com.julia.apd.chuckie.remote.ChuckNorrisApi
import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokeValueResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel

class FakeJokeApi : ChuckNorrisApi {
    var isFail = false

    override suspend fun getRandomJoke(): JokeResponseModel {
        if (!isFail) {
            return getJoke(1)
        } else {
            throw Exception("Failure")
        }
    }

    override suspend fun getRandomJoke(firstName: String, lastName: String): JokeResponseModel {
        if (!isFail) {
            return getNamedJoke(2, firstName, lastName)
        } else {
            throw Exception("Failure")
        }
    }

    override suspend fun getRandomJokes(numberOfJokes: Int): JokesResponseModel {
        if (!isFail) {
            return getJokeList(numberOfJokes)
        } else {
            throw Exception("Failure")
        }
    }

    private fun getJoke(id: Int): JokeResponseModel {
        val jokeStr = "jokey number $id"
        val joke = JokeValueResponseModel(id, jokeStr)
        return JokeResponseModel("success", joke)
    }

    private fun getNamedJoke(id: Int, firstName: String, lastName: String): JokeResponseModel {
        val jokeStr = "jokey number $id with $firstName $lastName"
        val joke = JokeValueResponseModel(id, jokeStr)
        return JokeResponseModel("success", joke)
    }

    private fun getJokeList(numberOfJokes: Int): JokesResponseModel {
        val jokeList = mutableListOf<JokeValueResponseModel>()
        if (numberOfJokes > 1) {
            for (i in 1..numberOfJokes) {
                val jokeStr = "jokey number $i"
                val joke = JokeValueResponseModel(i, jokeStr)
                jokeList.add(joke)
            }
        }
        return JokesResponseModel("success", jokeList.toList())
    }

}