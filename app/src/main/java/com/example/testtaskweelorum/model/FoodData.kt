package com.example.testtaskweelorum.model

data class FoodData(
    val name: String,
    val calories: String,
    val isFavorite: Boolean,
    val isMine: Boolean
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name$calories",
            "$name $calories",
            "$name.first() $calories.first()"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
