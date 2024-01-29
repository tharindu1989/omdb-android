package com.omdb.movie.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.omdb.movie.domain.movie.MovieDetails
import com.omdb.movie.presentation.common.LoadingIndicator
import com.omdb.movie.presentation.common.MovieCover
import com.omdb.movie.presentation.theme.Background
import com.omdb.movie.presentation.theme.Black50
import com.omdb.movie.presentation.theme.GrayLight
import com.omdb.movie.presentation.theme.White

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel(),
    movieId: String
) {
    val movie by viewModel.movie.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(movieId) {
        if (movie == null) {
            viewModel.getDetails(movieId)
        }
    }
    Surface {
        movie?.let { MovieDetailsView(it) { navController.popBackStack() } }
        if (isLoading) LoadingIndicator()
    }
}

@Composable
fun MovieDetailsView(movie: MovieDetails, onClickBack: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        MovieCover(cover = movie.poster, onClickBack = onClickBack)
        Column(modifier = Modifier.padding(all = 20.dp)) {
            Text(text = movie.title, color = White, fontSize = 24.sp) // Title
            Row(modifier = Modifier.padding(top = 12.dp)) {
                IconText(movie.runtime, Icons.Default.Info)
                Spacer(modifier = Modifier.width(20.dp))
                IconText(movie.imdbRating?.let { "$it (IMDb)" }, Icons.Default.Star)
            } // Ratings
            CustomDivider()
            TextCatalog(movie.released, movie.genre) // Release Dates
            CustomDivider()
            Text(text = "Synopsis", color = White, fontSize = 16.sp)
            Text(
                text = movie.plot ?: "",
                color = White,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            ) // Plot
        }

    }

}

@Composable
fun IconText(text: String?, icon: ImageVector) {
    text?.let {
        Row {
            Icon(icon, contentDescription = null, tint = GrayLight)
            Text(text = text, color = GrayLight, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun CustomDivider() {
    Divider(thickness = 1.dp, color = Black50, modifier = Modifier.padding(vertical = 12.dp))
}

@Composable
fun TextCatalog(releaseDate: String?, genre: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Release Date", color = White, fontSize = 16.sp)
            Text(text = releaseDate ?: "", color = GrayLight)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = "Genre", color = White, fontSize = 16.sp)
            Text(text = genre ?: "", color = GrayLight)
        }
    }
}