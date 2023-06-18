package com.example.testtaskweelorum.repository

import com.example.testtaskweelorum.model.FoodData

class FoodRepository {
    fun getAllFood(): List<FoodData> {
        return listOf(
            FoodData(
                name = "Вівсянка",
                calories = "250г, 360 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Банан",
                calories = "100г, 270 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Куряча грудка",
                calories = "230г, 410 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Лосось",
                calories = "140, 320 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Сир",
                calories = "50г, 140 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Гречка",
                calories = "250г, 230 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Куряче яйце",
                calories = "40г, 110 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Горіхи",
                calories = "100г, 380 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Нут",
                calories = "300г, 440 ккал",
                isFavorite = false,
                isMine = false,
            ),
            FoodData(
                name = "Рис",
                calories = "300г, 260 ккал",
                isFavorite = false,
                isMine = false,
            )
        )
    }

    fun getFavoriteFood(): List<FoodData> {
        return listOf(
            FoodData(
                name = "Вівсянка",
                calories = "250г, 360 ккал",
                isFavorite = true,
                isMine = false,
            ),
            FoodData(
                name = "Банан",
                calories = "100г, 270 ккал",
                isFavorite = true,
                isMine = false,
            ),
            FoodData(
                name = "Куряча грудка",
                calories = "230г, 410 ккал",
                isFavorite = true,
                isMine = false,
            )
        )
    }

    fun getMyFood(): List<FoodData> {
        return listOf(
            FoodData(
                name = "Вівсянка",
                calories = "250г, 360 ккал",
                isFavorite = false,
                isMine = true,
            ),
            FoodData(
                name = "Банан",
                calories = "100г, 270 ккал",
                isFavorite = false,
                isMine = true,
            ),
            FoodData(
                name = "Куряча грудка",
                calories = "230г, 410 ккал",
                isFavorite = false,
                isMine = true,
            )
        )
    }
}