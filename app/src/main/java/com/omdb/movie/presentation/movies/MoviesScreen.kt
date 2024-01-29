package com.omdb.movie.presentation.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.omdb.movie.presentation.common.LoadingIndicator
import com.omdb.movie.presentation.common.MovieCard
import com.omdb.movie.presentation.common.NotFound
import com.omdb.movie.presentation.theme.Background
import com.omdb.movie.presentation.theme.BackgroundLight
import com.omdb.movie.presentation.theme.Purple80
import com.omdb.movie.presentation.theme.White

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val searchText by viewModel.searchText.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isSearching.collectAsState()
    Surface {
        Column(
            Modifier
                .background(Background)
                .padding(all = 24.dp)
                .fillMaxSize()
        ) {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.systemBars
                )
            )
            Text(
                text = "Find Movies, Tv series, and more", color = White, // Font color
                fontSize = 24.sp, // Font size
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            SearchTextField(
                value = searchText ?: "",
                onValueChange = { viewModel.onSearch(it) }
            )
            if(isLoading) LoadingIndicator()
            if(movies.isEmpty() && !isLoading) NotFound()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(movies.size) { index ->
                    MovieCard(movies[index]) { movie ->
                        navController.navigate("details/${movie.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text("Search Movies", color = Purple80)
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = BackgroundLight,
            unfocusedContainerColor = BackgroundLight,
            disabledContainerColor = BackgroundLight,
            unfocusedBorderColor = BackgroundLight,
            focusedBorderColor = BackgroundLight,
            cursorColor = White,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
    )
}

