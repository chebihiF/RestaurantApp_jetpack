package org.doronco.restaurantapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantDetailsViewModel(private val stateHandle: SavedStateHandle): ViewModel(){
    val state = mutableStateOf<Restaurant?>(null)
    private var restInterface: RestaurantsApiService
    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://avid-racer-241421.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)
        val id = stateHandle.get<Int>("restaurant_id") ?: 0
        viewModelScope.launch(errorHandler) {
            val restaurant = getRemoteRestaurant(id)
            state.value = restaurant
        }

    }

    private suspend fun getRemoteRestaurant(id : Int): Restaurant {
        return withContext(Dispatchers.IO){
            try {
                val responseMap = restInterface.getRestaurant(id) // fetch data from firebase
                return@withContext responseMap.values.first()
            }catch (exception: Exception){
                when (exception) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        return@withContext restaurantsDao.getById(id) // fetch data from sqlite via room
                    }
                    else -> throw exception
                }
            }
        }
    }


}