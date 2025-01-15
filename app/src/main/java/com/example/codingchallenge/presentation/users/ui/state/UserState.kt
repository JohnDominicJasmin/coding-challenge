package com.example.codingchallenge.presentation.users.ui.state
sealed class UserState<out T> {
    data class Success<out T>(val data: T) : UserState<T>()
    data class Failure(val exception: Throwable) : UserState<Nothing>()
    data object Loading : UserState<Nothing>()

    companion object {
        fun <T> loading() = Loading as UserState<T>
    }
}
