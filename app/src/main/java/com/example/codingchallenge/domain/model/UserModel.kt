package com.example.codingchallenge.domain.model

data class UserModel (
    val id: String = "",
    val name: Name,
    val address: Address,
    val profilePicture: ProfilePicture
)