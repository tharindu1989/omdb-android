package com.omdb.movie.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.omdb.movie.presentation.theme.Background
import com.omdb.movie.presentation.theme.White

@Composable
fun MovieCover(cover: String?, onClickBack: () -> Unit) {
    Box(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = rememberAsyncImagePainter(cover),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Background),
                    )
                )
        )
        Column {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.systemBars
                )
            )
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = White,
                modifier =
                Modifier
                    .size(56.dp)
                    .clickable { onClickBack() },
            )
        }
    }
}