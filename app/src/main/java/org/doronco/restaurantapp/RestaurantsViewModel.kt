package org.doronco.restaurantapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsViewModel() : ViewModel() {

    private var restInterface: RestaurantsApiService
    val state = mutableStateOf(emptyList<Restaurant>())
    private lateinit var restaurantsCall: Call<List<Restaurant>>
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(
                "https://avid-racer-241421.firebaseio.com/"
            )
            .build()
        restInterface = retrofit.create(
            RestaurantsApiService::class.java
        )
        getRestaurants()
    }

    override fun onCleared() {
        super.onCleared()
        restaurantsCall.cancel()
    }

    private fun getRestaurants() {

    }

    fun toggleFavorite(id: Int) {
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        state.value = restaurants
    }
}