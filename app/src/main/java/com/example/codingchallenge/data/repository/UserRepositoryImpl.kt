package com.example.codingchallenge.data.repository

import com.example.codingchallenge.data.RandomUserApiService
import com.example.codingchallenge.data.mapper.UserMapper.toUserModel
import com.example.codingchallenge.domain.model.UserModel
import com.example.codingchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class UserRepositoryImpl(
    private val api: RandomUserApiService
): UserRepository {
    override suspend fun getUsers(page: Int, numberOfResults: Int): Flow<Result<List<UserModel>>>  = flow {
        emit(
            kotlin.runCatching {
               val userDto  =  api.getUsers(page, numberOfResults)
                userDto.results.map { it.toUserModel() }
            }
        )
    }.retry(3) { cause ->
        cause is IOException || cause is HttpException
    }.catch { cause ->
        emit(Result.failure(cause))
    }
}