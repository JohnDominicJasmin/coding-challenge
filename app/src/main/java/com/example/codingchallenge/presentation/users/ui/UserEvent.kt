package com.example.codingchallenge.presentation.users.ui

sealed class UserEvent {
    data class GetUsers(val page: Int, val numberOfResult: Int) : UserEvent()
}