package com.julia.apd.chuckie.models

import android.os.Build
import android.text.Html
import com.julia.apd.chuckie.remote.responsemodels.JokeResponseModel
import com.julia.apd.chuckie.remote.responsemodels.JokesResponseModel


data class JokeModel(val id: Int, val joke: String) {
    companion object {
        fun map(response: JokeResponseModel) =
            JokeModel(response.value.id, fromHtml(response.value.joke))

        fun fromHtml(htmlText: String): String {
            val text = if (Build.VERSION.SDK_INT >= 24) {
                Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(htmlText)
            }
            return text.toString().trim()
        }
    }
}

data class JokesModel(val jokes: List<JokeModel>) {
    companion object {
        fun map(response: JokesResponseModel) = response.value.map {
            JokeModel(it.id, JokeModel.fromHtml(it.joke))
        }
    }
}

