package com.example.codingchallenge.data.data_source.network.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Login(
    @SerializedName("md5")
    val md5: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("salt")
    val salt: String,
    @SerializedName("sha1")
    val sha1: String,
    @SerializedName("sha256")
    val sha256: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("uuid")
    val uuid: String
)