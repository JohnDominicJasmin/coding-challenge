package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Registered(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)