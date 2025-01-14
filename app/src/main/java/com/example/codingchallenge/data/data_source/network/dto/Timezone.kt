package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Timezone(
    @SerializedName("description")
    val description: String,
    @SerializedName("offset")
    val offset: String
)