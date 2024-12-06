package com.example.home.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.useCase.AddMovieToWishListUseCase
import com.example.home.domain.useCase.FetchTheFirstTenPopularMoviesUseCase
import com.example.home.domain.useCase.RemoveMovieFromWishListUseCase
import com.example.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private var fetchTheFirstTenPopularMoviesUseCase: FetchTheFirstTenPopularMoviesUseCase,
    private var addMovieToWishListUseCase: AddMovieToWishListUseCase,
    private var removeMovieFromWishListUseCase: RemoveMovieFromWishListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<HomeUIState> =
        MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state.asStateFlow()

    fun onAppear() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies()
        }
    }

    suspend fun getPopularMovies() {
        val result = fetchTheFirstTenPopularMoviesUseCase()
        result.collect { response ->
            when {
                response.isLoading() -> {
                    _state.update {
                        it.copy(
                            showLoading = true
                        )
                    }
                }

                response.isError() -> {
                    _state.update {
                        it.copy(
                            showLoading = false
                        )
                    }
                }

                response.isSuccess() -> {
                    _state.update {
                        it.copy(
                            showLoading = false,
                            moviesList = response.data ?: listOf()
                        )
                    }
                }
            }
        }
    }

    fun onMovieClick(movieUI: MovieUI){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    moviesList = state.value.moviesList.map {
                        it.copy(
                            isWishListed = if(it.id==movieUI.id) !it.isWishListed else it.isWishListed
                        )
                    }
                )
            }
            if(!movieUI.isWishListed)
                addMovieToWishListUseCase(movieUI=movieUI)
            else
                removeMovieFromWishListUseCase(movieUI=movieUI)
        }
    }
}