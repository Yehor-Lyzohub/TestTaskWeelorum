package com.example.testtaskweelorum.repository

import com.example.testtaskweelorum.model.WorkoutData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutRepository {

    private val _state: MutableStateFlow<List<WorkoutData>> = MutableStateFlow(listOf())
    val state = _state.asStateFlow()

    fun getExercises() {
        _state.value = excercises
    }

    fun expandTraining(id: String) {
        val updatedExercises = _state.value.map { workout ->
            if (workout.id == id) {
                workout.copy(isExpanded = !workout.isExpanded)
            } else {
                workout
            }
        }

        _state.value = updatedExercises
    }

    fun addSet(id: String) {
        val updatedExercises = _state.value.map { workout ->
            if (workout.id == id) {
                workout.copy(
                    sets = workout.sets + listOf(
                        WorkoutData.WorkoutSet(
                            (workout.sets.size + 1).toString(),
                            "25.5кг х 12",
                            30,
                            15,
                            false
                        )
                    )
                )
            } else {
                workout
            }
        }

        _state.value = updatedExercises
    }

    fun finishSet(workoutId: String, setId: String) {
        val updatedExercises = _state.value.map { workout ->
            if (workout.id == workoutId) {
                val updatedSets = workout.sets.map { set ->
                    if (set.id == setId) {
                        set.copy(isDone = true)
                    } else {
                        set
                    }
                }
                workout.copy(sets = updatedSets)
            } else {
                workout
            }
        }

        _state.value = updatedExercises
    }
}