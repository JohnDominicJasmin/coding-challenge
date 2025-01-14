package com.example.codingchallenge.feature_users.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Dob(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)