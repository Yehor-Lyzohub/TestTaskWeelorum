package com.example.testtaskweelorum.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testtaskweelorum.ui.AddMealScreen
import com.example.testtaskweelorum.ui.WorkoutDetailsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AddMealScreen.route) {
        composable(route = Screen.AddMealScreen.route) {
            AddMealScreen(navigateToTraining = {
                navController.navigate(Screen.WorkoutDetailsScreen.route)
            })
        }
        composable(route = Screen.WorkoutDetailsScreen.route) {
            WorkoutDetailsScreen()
        }
    }
}