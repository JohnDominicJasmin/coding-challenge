package com.example.codingchallenge.feature_users.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val resultDtos: List<ResultDto>
)