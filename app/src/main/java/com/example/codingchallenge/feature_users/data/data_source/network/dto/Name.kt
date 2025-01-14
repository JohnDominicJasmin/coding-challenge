package com.example.codingchallenge.feature_users.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Name(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("title")
    val title: String
)