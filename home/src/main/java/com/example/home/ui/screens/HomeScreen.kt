package com.example.home.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.home.ui.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel){
    LaunchedEffect(Unit) {
        viewModel.getPopularMovies()
    }
}