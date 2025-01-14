package com.example.codingchallenge.domain.model

data class Name(val firstName: String = "", val lastName: String = "", val title: String = "") {
    val fullName: String
        get() = "$title $firstName $lastName"
}
