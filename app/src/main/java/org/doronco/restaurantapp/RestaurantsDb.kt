package org.doronco.restaurantapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Restaurant::class],
    version = 1,
    exportSchema = false)
abstract class RestaurantsDb : RoomDatabase() {
    abstract val dao : RestaurantsDao
    companion object {
        private fun buildDatabase(context: Context):RestaurantsDb = Room.databaseBuilder(
            context.applicationContext,
            RestaurantsDb::class.java,
            "restaurants_database").fallbackToDestructiveMigration().build()
        // create instance of Dao ... multiThread .. <=> Firebase !!
    }
}