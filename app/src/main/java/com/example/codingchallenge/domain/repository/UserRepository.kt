package com.example.codingchallenge.domain.repository

import com.example.codingchallenge.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
suspend fun getUsers(page:Int, numberOfResults: Int): Flow<Result<List<UserModel>>>
}