package com.example.feature.wishlist.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.dispatchers.IoDispatcher
import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.useCase.FetchMoviesFromWatchListUseCase
import com.example.movie_data.domain.useCase.RemoveMovieFromWatchListUseCase
import com.example.feature.wishlist.model.WatchList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val fetchMoviesFromWatchListUseCase: FetchMoviesFromWatchListUseCase,
    private val removeMovieFromWatchListUseCase: RemoveMovieFromWatchListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
):ViewModel() {

    private val _state: MutableStateFlow<WatchList> =
        MutableStateFlow(WatchList())
    val state: StateFlow<WatchList> = _state.asStateFlow()

    fun onAppear(){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    watchListMovies = fetchMoviesFromWatchListUseCase()
                )
            }
        }
    }

    fun onMovieClick(movieUI: MovieUI){
        viewModelScope.launch(dispatcher) {
            removeMovieFromWatchListUseCase(movieUI.copy(isWishListed = false))
            _state.update {
                it.copy(
                    watchListMovies = state.value.watchListMovies.filter { it.id!=movieUI.id }
                )
            }
        }
    }
}