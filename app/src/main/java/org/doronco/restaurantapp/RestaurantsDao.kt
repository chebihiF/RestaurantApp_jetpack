package org.doronco.restaurantapp

import androidx.room.*

@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants WHERE r_id=:id")
    suspend fun getById(id: Int) : Restaurant

    @Query("SELECT * FROM restaurants")
    suspend fun getAll() : List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)

    @Update(entity = Restaurant::class)
    suspend fun update(restaurant: Restaurant)
}