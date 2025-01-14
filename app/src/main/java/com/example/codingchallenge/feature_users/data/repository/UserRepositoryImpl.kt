package com.example.codingchallenge.feature_users.data.repository

import com.example.codingchallenge.feature_users.data.RandomUserApiService
import com.example.codingchallenge.feature_users.data.mapper.UserMapper.toUserModel
import com.example.codingchallenge.feature_users.domain.model.UserModel
import com.example.codingchallenge.feature_users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(
    private val api: RandomUserApiService
): UserRepository {
    override suspend fun getUsers(page: Int, numberOfResults: Int): Flow<Result<List<UserModel>>>  = flow {
        emit(
            kotlin.runCatching {
               val userDto  =  api.getUsers(page, numberOfResults)
                userDto.resultDtos.map { it.toUserModel() }
            }
        )
    }.retry(3) { cause ->
        cause is IOException || cause is HttpException
    }.catch { cause ->
        emit(Result.failure(cause))
    }
}