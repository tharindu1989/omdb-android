package com.omdb.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.omdb.movie.presentation.details.DetailsScreen
import com.omdb.movie.presentation.movies.MoviesScreen
import com.omdb.movie.presentation.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "movies") {
                    composable("movies") {
                        MoviesScreen(navController = navController)
                    }
                    composable(
                        "details/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId")
                        if (movieId != null) {
                            DetailsScreen(navController = navController, movieId = movieId)
                        }
                    }
                }
            }
        }
    }
}