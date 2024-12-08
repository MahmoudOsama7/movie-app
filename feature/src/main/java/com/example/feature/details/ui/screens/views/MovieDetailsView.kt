package com.example.feature.details.ui.screens.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature.details.ui.screens.views.GenreSection
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun MovieDetailsView(
    movie: MovieUI,
    onWishlistToggle: (MovieUI) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = movie.originalTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.originalTitle,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "⭐ ${movie.voteAverage} (${movie.voteCount} votes)",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        GenreSection(movie.genres)
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            lineHeight = 20.sp
        )
        Button(
            onClick = { onWishlistToggle(movie) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (movie.isWishListed) Color.Red else Color.Gray
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = if (movie.isWishListed) "Remove from Wishlist" else "Add to Wishlist",
                color = Color.White
            )
        }
    }
}

