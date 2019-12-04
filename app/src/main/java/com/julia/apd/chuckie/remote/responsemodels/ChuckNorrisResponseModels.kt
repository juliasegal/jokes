package com.julia.apd.chuckie.remote.responsemodels

import com.google.gson.annotations.SerializedName

data class JokesResponseModel(
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: List<JokeValueResponseModel>
)

data class JokeResponseModel(
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: JokeValueResponseModel
)

data class JokeValueResponseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("joke") val joke: String
)

