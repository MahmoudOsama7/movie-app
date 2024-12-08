package com.example.feature.wishlist.ui.screens.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun WatchListItemView(
    movieUI: MovieUI,
    onMovieClick: (MovieUI) -> Unit,
    onMovieClicked:(MovieUI)->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClicked(movieUI) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = movieUI.poster,
                contentDescription = movieUI.originalTitle,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movieUI.originalTitle,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movieUI.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⭐ ${movieUI.voteAverage} (${movieUI.voteCount} votes)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onMovieClick(movieUI) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        modifier = Modifier.clickable { onMovieClick(movieUI) },
                        text = "Remove from WatchList",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
