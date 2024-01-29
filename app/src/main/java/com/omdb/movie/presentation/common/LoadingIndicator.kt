package com.omdb.movie.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.omdb.movie.presentation.theme.Background
import com.omdb.movie.presentation.theme.GrayLight

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = GrayLight,
            strokeWidth = 5.dp
        )
    }
}