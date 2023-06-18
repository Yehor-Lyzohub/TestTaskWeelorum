package com.example.testtaskweelorum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskweelorum.model.WorkoutData
import com.example.testtaskweelorum.repository.FoodRepository
import com.example.testtaskweelorum.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {

    private val foodRepository = FoodRepository()
    private val workoutRepository = WorkoutRepository()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _food = MutableStateFlow(foodRepository.getAllFood())

    val food = searchText
        .combine(_food) { text, food ->
            if (text.isBlank()) {
                food
            } else {
                food.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _food.value
        )

    private val _favFood = MutableStateFlow(foodRepository.getFavoriteFood())
    val favFood = _favFood.asStateFlow()

    private val _myFood = MutableStateFlow(foodRepository.getMyFood())
    val myFood = _myFood.asStateFlow()

    private val _selectedTab = MutableStateFlow(FoodTab.SEARCH)
    val selectedTab = _selectedTab.asStateFlow()

    private val _exerciseItem = MutableStateFlow(workoutRepository.allExerciseItems())
    val exerciseItem = _exerciseItem.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun changeSelectedTab(newSelectedTab: FoodTab) {
        _selectedTab.value = newSelectedTab
    }

    fun expandTraining(id: String) {
        val updatedExercises = _exerciseItem.value.map { workout ->
            if (workout.id == id) {
                workout.copy(isExpanded = !workout.isExpanded)
            } else {
                workout
            }
        }

        _exerciseItem.value = updatedExercises
    }

    fun addSet(id: String) {
        val updatedExercises = _exerciseItem.value.map { workout ->
            if (workout.id == id) {
                workout.copy(sets = workout.sets + listOf(WorkoutData.WorkoutSet((workout.sets.size + 1).toString(), "25.5кг х 12", 30, 15, false)))
            } else {
                workout
            }
        }

        _exerciseItem.value = updatedExercises
    }

    fun finishSet(workoutId: String, setId: String) {
        val updatedExercises = _exerciseItem.value.map { workout ->
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

        _exerciseItem.value = updatedExercises
    }
}