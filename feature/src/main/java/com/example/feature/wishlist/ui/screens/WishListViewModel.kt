package com.example.feature.wishlist.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.dispatchers.IoDispatcher
import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.useCase.FetchMoviesFromWishListUseCase
import com.example.movie_data.domain.useCase.RemoveMovieFromWishListUseCase
import com.example.feature.wishlist.model.WishListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val fetchMoviesFromWishListUseCase: FetchMoviesFromWishListUseCase,
    private val removeMovieFromWishListUseCase: RemoveMovieFromWishListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _state: MutableStateFlow<WishListUiState> =
        MutableStateFlow(WishListUiState())
    val state: StateFlow<WishListUiState> = _state.asStateFlow()

    fun onAppear(){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    wishListMovies = fetchMoviesFromWishListUseCase()
                )
            }
        }
    }

    fun onMovieClick(movieUI: MovieUI){
        viewModelScope.launch(dispatcher) {
            removeMovieFromWishListUseCase(movieUI.copy(isWishListed = false))
            _state.update {
                it.copy(
                    wishListMovies = state.value.wishListMovies.filter { it.id!=movieUI.id }
                )
            }
        }
    }
}