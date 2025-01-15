package com.example.codingchallenge.presentation.users.ui


sealed class UserEvent {
    data class GetUsers(val page: Int, val numberOfResult: Int) : UserEvent()
    data class GetUserById( val numberOfResults: Int, val id: String) : UserEvent()
}