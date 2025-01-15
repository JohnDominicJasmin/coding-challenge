package com.example.codingchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.codingchallenge.presentation.users.ui.screens.ProfileScreen
import com.example.codingchallenge.presentation.users.ui.screens.UsersScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.UserList.route) {
        composable(Screens.UserList.route) {
            UsersScreen(navController = navController)
        }
        composable(
            route = Screens.UserDetails.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("numberOfResult") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val numberOfResult = backStackEntry.arguments?.getInt("numberOfResult") ?: 0
            ProfileScreen(navController = navController, id = id, numberOfResults = numberOfResult)
        }
    }
}


fun NavController.navigateScreen(
    route: String) {
    navigate(route) {
        popUpTo(route) {
            saveState = true

        }
        restoreState = true
        launchSingleTop = true
    }
}

fun NavController.goBack() {
    if (previousBackStackEntry != null) {
        popBackStack()
    }
}