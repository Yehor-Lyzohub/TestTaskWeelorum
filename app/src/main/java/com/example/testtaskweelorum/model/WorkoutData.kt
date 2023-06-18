package com.example.testtaskweelorum.model

data class WorkoutData(
    val id: String,
    val name: String,
    val reps: String,
    val weight: String,
    val sets: List<WorkoutSet> = listOf(),
    val isExpanded: Boolean = false,
) {
    data class WorkoutSet(
        val id: String,
        val previous: String,
        val weight: Int,
        val reps: Int,
        val isDone: Boolean
    )
}

/*
val allExerciseItems = listOf(
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

 */
