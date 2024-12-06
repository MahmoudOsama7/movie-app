package com.example.details.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.details.ui.screens.views.ActingCastLazyRow
import com.example.home.domain.mapper.MovieUI

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackPressed:()->Unit
){
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initArguments()
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        BackButton(
            onBackClick = onBackPressed
        )
        MovieDetailsView(
            movie = state.value.movieDetails,
            onWishlistToggle = viewModel::onWishListClicked,
        )
        ActingCastLazyRow(state.value.movieCast)
    }
}


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
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
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

@Composable
fun BackButton(onBackClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier
            .size(32.dp)
            .clickable { onBackClick() },
        tint = Color.Black
    )
}
