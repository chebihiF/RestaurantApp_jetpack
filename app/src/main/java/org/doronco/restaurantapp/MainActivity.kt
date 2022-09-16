package org.doronco.restaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.doronco.restaurantapp.ui.theme.RestaurantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme {
                PatientScreen()
            //RestaurantsApp()
            }
        }
    }
}

@Composable
private fun RestaurantsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "restaurants") {

        composable(route = "restaurants") {
            RestaurantsScreen(
                onItemClick = { id -> navController.navigate("restaurants/$id")},
                onExampleClick = { navController.navigate("restaurants/example") }
            )
        }

        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id"){
                type = NavType.IntType
            })
        ) {
            RestaurantDetailsScreen()
        }

        composable(route = "restaurants/example"){
            RestaurantExampleScreen()
        }
    }
}

