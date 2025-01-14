package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Street(
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: Int
)