package com.example.testtaskweelorum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val foodState = foodRepository.state

    val food = searchText
        .combine(foodState) { text, food ->
            if (text.isBlank()) {
                food.allFood
            } else {
                food.allFood.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            foodState.value.allFood
        )

    private val _selectedTab = MutableStateFlow(FoodTab.SEARCH)
    val selectedTab = _selectedTab.asStateFlow()

    val exerciseItem = workoutRepository.state

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        foodRepository.getAllFood()
        workoutRepository.getExercises()
    }

    private fun getFavoriteFood() {
        foodRepository.getFavoriteFood()
    }

    private fun getMyFood() {
        foodRepository.getMyFood()
    }

    fun changeSelectedTab(newSelectedTab: FoodTab) {
        when (newSelectedTab) {
            FoodTab.SEARCH -> foodRepository.getAllFood()
            FoodTab.FAVORITE -> getFavoriteFood()
            FoodTab.MY_FOOD -> getMyFood()
        }
        _selectedTab.value = newSelectedTab
    }

    fun expandTraining(id: String) {
        workoutRepository.expandTraining(id)
    }

    fun addSet(id: String) {
        workoutRepository.addSet(id)
    }

    fun finishSet(workoutId: String, setId: String) {
        workoutRepository.finishSet(workoutId, setId)
    }
}