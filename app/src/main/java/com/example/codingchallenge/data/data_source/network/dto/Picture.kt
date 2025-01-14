package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Picture(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)