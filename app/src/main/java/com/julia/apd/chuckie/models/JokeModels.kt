package com.julia.apd.chuckie.models

import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel


data class JokeModel(val id: Int, val joke: String) {
    companion object {
        fun map(response: JokeResponseModel) = JokeModel(response.value.id, response.value.joke)
    }
}

data class JokesModel(val jokes: List<JokeModel>) {
    companion object {
        fun map(response: JokesResponseModel) = response.value.map { JokeModel(it.id, it.joke)
        }
    }
}

