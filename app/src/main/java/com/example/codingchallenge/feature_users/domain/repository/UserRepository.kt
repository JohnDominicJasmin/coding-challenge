package com.example.codingchallenge.feature_users.domain.repository

import com.example.codingchallenge.feature_users.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
suspend fun getUsers(page:Int, numberOfResults: Int): Flow<Result<List<UserModel>>>
}