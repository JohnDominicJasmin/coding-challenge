package com.example.codingchallenge.feature_users.data

import com.example.codingchallenge.feature_users.data.data_source.network.dto.ResultDto
import com.example.codingchallenge.feature_users.data.data_source.network.dto.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {
    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): UserResponse
}
