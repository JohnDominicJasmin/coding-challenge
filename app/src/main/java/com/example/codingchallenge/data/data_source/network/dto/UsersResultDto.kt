package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UsersResultDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)