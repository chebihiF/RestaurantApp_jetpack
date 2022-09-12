package org.doronco.restaurantapp

import androidx.lifecycle.ViewModel

class RestaurantsViewModel():ViewModel() {
    fun getRestaurants() = dummyRestaurants
}