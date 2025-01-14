package com.example.codingchallenge.data

import com.example.codingchallenge.data.data_source.network.dto.UsersResultDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {
    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): UsersResultDto
}
