package com.omdb.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omdb.movie.presentation.movies.MoviesScreen
import com.omdb.movie.presentation.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "movies") {
                    composable("movies") {
                        MoviesScreen(navController = navController)
                    }
                }
            }
        }
    }
}