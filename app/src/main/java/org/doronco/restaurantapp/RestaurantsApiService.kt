package org.doronco.restaurantapp


import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants() : List<Restaurant>
    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurant(@Query("equalTo") id : Int) : Map<String,Restaurant>
    @PUT("restaurants.json?orderBy=\"r_id\"")
    suspend fun updateRestaurant(@Query("equalTo") id: Int, @Body restaurant: Restaurant)
}