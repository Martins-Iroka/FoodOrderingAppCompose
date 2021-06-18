package com.martdev.foodorderingappcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.martdev.foodorderingappcompose.R
import java.io.Serializable


val foods = listOf(
    Food(R.drawable.foodie, "Veggie tomato mix"),
    Food(R.drawable.food2, "Fried chicken m."),
    Food(R.drawable.food3, "Egg and Cucumber"),
    Food(R.drawable.food4, "Moi-moi and ekpa.")
)
data class Food(
    val food: Int,
    val name: String,
    val price: String = "#1,900"
)

class FoodOrderingVM : ViewModel() {

    var foodList by mutableStateOf(foods)
    private set

    fun removeFood(food: Food) {
        foodList = foodList.toMutableList().also { it.remove(food) }
        println("remove food was called")
    }
}