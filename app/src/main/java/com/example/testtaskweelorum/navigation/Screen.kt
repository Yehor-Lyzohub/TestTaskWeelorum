package com.example.testtaskweelorum.navigation

sealed class Screen(val route: String) {
    object AddMealScreen : Screen("add_meal_screen")
    object WorkoutDetailsScreen : Screen("workout_details_screen")
}
