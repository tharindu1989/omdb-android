package com.omdb.movie.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.omdb.movie.presentation.theme.Background
import com.omdb.movie.presentation.theme.GrayLight

@Composable
fun NotFound() {
    Box(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
    ) {
        Text(
            text = "Movie not found",
            modifier = Modifier.align(Alignment.Center),
            color = GrayLight,
        )
    }
}