package com.example.home.ui.screens.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun VerticalMovieListItemView(
    movie: MovieUI,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Each item takes up the full width
            .padding(bottom = 16.dp), // Padding between items
        shape = MaterialTheme.shapes.medium, // Medium rounded corners
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding within the card
        ) {
            // Use AsyncImage to load the poster image efficiently
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.originalTitle,
                modifier = Modifier
                    .size(100.dp) // Size of the image
                    .clip(RoundedCornerShape(8.dp)) // Image shape with rounded corners
                    .border(2.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(8.dp)) // Optional border
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f) // This makes the Column take the remaining space
            ) {
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.headlineSmall, // Using a larger text style for better visibility
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium, // Using body2 for better text visibility
                    maxLines = 3, // Limiting lines for overview
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rating: ${movie.voteAverage}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "(${movie.voteCount} votes)",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = if (movie.isWishListed) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (movie.isWishListed) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
