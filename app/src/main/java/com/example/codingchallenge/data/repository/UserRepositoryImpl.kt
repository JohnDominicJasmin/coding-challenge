package com.example.codingchallenge.data.repository

import com.example.codingchallenge.core.di.Constants.NUMBER_OF_RETRIES
import com.example.codingchallenge.data.RandomUserApiService
import com.example.codingchallenge.data.mapper.UserMapper.toUserDetails
import com.example.codingchallenge.data.mapper.UserMapper.toUserModel
import com.example.codingchallenge.domain.model.UserDetails
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
    override suspend fun getUsers(numberOfResults: Int): Flow<Result<List<UserModel>>>  = flow {
        emit(
            kotlin.runCatching {
               val userDto  =  api.getUsers(numberOfResults)
                userDto.results.filter { it.id.value != null }.map { it.toUserModel() }
            }
        )
    }.retry(NUMBER_OF_RETRIES) { cause ->
        cause is IOException || cause is HttpException
    }.catch { cause ->
        emit(Result.failure(cause))
    }

    override suspend fun getUserById( numberOfResults: Int, id: String): Flow<Result<UserDetails>> = flow {
        emit(
            kotlin.runCatching {
                val userDto = api.getUsers( results = numberOfResults, )
                val user = userDto.results.firstOrNull { it.id.value == id }

                user?.toUserDetails()
                ?: throw Exception("User not found")
            }
        )
    }.retry(NUMBER_OF_RETRIES) { cause ->
        cause is IOException || cause is HttpException
    }.catch { cause ->
        emit(Result.failure(cause))
    }
}