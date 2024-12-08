package com.example.feature.details.ui.screens.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movie_data.domain.model.GenreUI

@Composable
fun GenreChip(genre: GenreUI) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}