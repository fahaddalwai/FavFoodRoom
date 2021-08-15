package com.example.favfoodroom.viewfavfood

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.favfoodroom.database.Food
import com.example.favfoodroom.database.FoodDatabaseDao
import com.example.favfoodroom.network.FoodApi
import com.example.favfoodroom.network.Photo
import kotlinx.coroutines.launch

class ViewfavfoodViewModel(
val database: FoodDatabaseDao,
application: Application
) : AndroidViewModel(application) {

    val allFood=database.getAllFood()       //Room uses a background thread for queries which returns LiveData

    val allFoodZ:LiveData<List<Food>>
        get() {
            return allFood
        }


    private val _foodItem= MutableLiveData<Food>()
    val foodItem: LiveData<Food>
        get() = _foodItem

    fun putValueToFoodItem(food: Food) {
        _foodItem.value = food
    }

    fun SetFoodItemAsNull(){
        _foodItem.value = null
    }




    // Event action which triggers the end of the all facts and tells whether to go start fragment or not
    private val _eventStartPressed = MutableLiveData<Boolean>()
    val eventStartPressed: LiveData<Boolean>
        get() = _eventStartPressed


    fun setEventStartPressedToFalse() {
        _eventStartPressed.value = false
    }

    fun setEventStartPressedToTrue() {
        _eventStartPressed.value = true
    }

    init{
        setEventStartPressedToFalse()
        SetFoodItemAsNull()
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        database.clear()

    }

}

