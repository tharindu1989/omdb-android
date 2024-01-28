package com.omdb.movie.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omdb.movie.domain.movie.MovieData
import coil.compose.rememberAsyncImagePainter
import com.omdb.movie.presentation.theme.White


@Composable
fun MovieCard(movie: MovieData, onItemClick: (movie: MovieData) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onItemClick(movie) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.image),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(184.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = movie.title,
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            fontSize = 16.sp
        )
    }
}