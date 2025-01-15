package com.example.codingchallenge.domain.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        fun <T> loading() = Loading as Result<T>
    }

    val isSuccess get() = this is Success
    val isFailure get() = this is Failure
}