package com.julia.apd.chuckie.models

import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel


data class JokeModel(val id: Int, val joke: String) {
    companion object {
        fun map(response: JokeResponseModel) = JokeModel(response.value.id,response.value.joke)
    }
}

