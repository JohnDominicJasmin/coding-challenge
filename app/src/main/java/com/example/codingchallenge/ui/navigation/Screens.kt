package com.example.codingchallenge.ui.navigation

sealed class Screens(val route: String) {

    data object UserList : Screens("user_list")

    data object UserDetails : Screens("user_details?id={id}&numberOfResult={numberOfResult}") {
        fun passArguments(id: String, numberOfResult: Int): String {
            return "user_details?id=$id&numberOfResult=$numberOfResult"
        }
    }
}
