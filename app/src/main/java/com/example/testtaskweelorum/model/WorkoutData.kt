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
