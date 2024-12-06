package com.example.details.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.details.ui.screens.DetailsScreen
import com.example.details.ui.screens.DetailsViewModel

const val Details_ROUTE = "details"
const val MOVIE_ID = "movie_id"
const val detailsArguments = "/$MOVIE_ID={$MOVIE_ID}/"

fun NavHostController.navigateToDetailsScreen(
    movieID:Int
){
    navigate("$Details_ROUTE/$MOVIE_ID=$movieID/")
}

fun NavGraphBuilder.detailsScreen(
    onBackPressed:()->Unit
) {
    composable(route = Details_ROUTE + detailsArguments) {
        val viewModel:DetailsViewModel= hiltViewModel()
        DetailsScreen(
            viewModel=viewModel,
            onBackPressed=onBackPressed
        )
    }
}