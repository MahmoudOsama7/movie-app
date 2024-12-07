package com.example.home.ui.screens.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.home.domain.mapper.MovieUI

@Composable
fun MovieItemView(
    movie: MovieUI,
    onMovieClicked:(MovieUI)->Unit,
    onFavouriteClicked: (MovieUI) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .clickable { onMovieClicked(movie) }
            .width(150.dp)
            .height(250.dp)
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    model = movie.poster,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Icon(
                    imageVector = if (movie.isWishListed) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (movie.isWishListed) Color.Red else Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            onFavouriteClicked(movie)
                        }
                )
            }
            BasicText(
                text = movie.originalTitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(8.dp)
            )

            BasicText(
                text = "Rating: ${movie.voteAverage}",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
