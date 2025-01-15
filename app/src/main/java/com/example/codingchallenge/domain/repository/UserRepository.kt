package com.example.codingchallenge.domain.repository

import com.example.codingchallenge.domain.model.UserDetails
import com.example.codingchallenge.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
suspend fun getUsers(numberOfResults: Int): Flow<Result<List<UserModel>>>
    suspend fun getUserById(numberOfResults: Int, id: String): Flow<Result<UserDetails>>
}