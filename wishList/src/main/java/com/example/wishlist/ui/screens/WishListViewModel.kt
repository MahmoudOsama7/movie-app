package com.example.wishlist.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_data.domain.mapper.MovieUI
import com.example.home.domain.useCase.FetchMoviesFromWishListUseCase
import com.example.movie_data.domain.useCase.RemoveMovieFromWishListUseCase
import com.example.wishlist.model.WishListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val fetchMoviesFromWishListUseCase: FetchMoviesFromWishListUseCase,
    private val removeMovieFromWishListUseCase: RemoveMovieFromWishListUseCase
):ViewModel() {

    private val _state: MutableStateFlow<WishListUiState> =
        MutableStateFlow(WishListUiState())
    val state: StateFlow<WishListUiState> = _state.asStateFlow()

    fun onAppear(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    wishListMovies = fetchMoviesFromWishListUseCase()
                )
            }
        }
    }

    fun onMovieClick(movieUI: MovieUI){
        viewModelScope.launch(Dispatchers.IO) {
            removeMovieFromWishListUseCase(movieUI.copy(isWishListed = false))
            _state.update {
                it.copy(
                    wishListMovies = state.value.wishListMovies.filter { it.id!=movieUI.id }
                )
            }
        }
    }
}