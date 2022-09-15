package org.doronco.restaurantapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantDetailsViewModel(): ViewModel(){
    val state = mutableStateOf<Restaurant?>(null)
    private var restInterface: RestaurantsApiService

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://avid-racer-241421.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)
        viewModelScope.launch(errorHandler) {
            val restaurant = getRemoteRestaurant(2)
            state.value = restaurant
        }

    }

    private suspend fun getRemoteRestaurant(id : Int): Restaurant {
        return withContext(Dispatchers.IO){
            val responseMap = restInterface.getRestaurant(id)
            return@withContext responseMap.values.first()
        }
    }


}