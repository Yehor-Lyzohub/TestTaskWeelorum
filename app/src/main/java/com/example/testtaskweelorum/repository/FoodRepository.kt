package com.example.testtaskweelorum.repository

import com.example.testtaskweelorum.model.FoodData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FoodState(
    val allFood: List<FoodData> = listOf(),
    val favFood: List<FoodData> = listOf(),
    val myFood: List<FoodData> = listOf()
)

class FoodRepository {

    private val _state: MutableStateFlow<FoodState> = MutableStateFlow(FoodState())
    val state = _state.asStateFlow()

    fun getAllFood() {
        _state.value = _state.value.copy(allFood = allFoodList)
    }

    fun getFavoriteFood() {
        _state.value = _state.value.copy(favFood = favFoodList)
    }

    fun getMyFood() {
        _state.value = _state.value.copy(myFood = myFoodList)
    }
}