package com.example.testtaskweelorum.repository

import com.example.testtaskweelorum.model.WorkoutData

class WorkoutRepository {
    fun allExerciseItems(): List<WorkoutData> {
        return listOf(
            WorkoutData(
                id = "1",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "2",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "3",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "4",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "5",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "6",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "7",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            ),
            WorkoutData(
                id = "8",
                name = "Станова тяга",
                reps = "15 повторень",
                weight = "--кг"
            )
        )
    }
}