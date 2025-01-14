package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Id(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)