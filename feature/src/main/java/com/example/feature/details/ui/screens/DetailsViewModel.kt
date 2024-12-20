package com.example.feature.details.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.dispatchers.IoDispatcher
import com.example.feature.details.model.DetailsUiState
import com.example.feature.details.navigation.MOVIE_ID
import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.useCase.AddMovieToWishListUseCase
import com.example.movie_data.domain.useCase.FetchMovieActingCastUseCase
import com.example.movie_data.domain.useCase.FetchMovieDetailsUseCase
import com.example.movie_data.domain.useCase.FetchSimilarMoviesUseCase
import com.example.movie_data.domain.useCase.RemoveMovieFromWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private var fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private var savedStateHandle: SavedStateHandle,
    private var addMovieToWishListUseCase: AddMovieToWishListUseCase,
    private var removeMovieFromWatchListUseCase: RemoveMovieFromWatchListUseCase,
    private var fetchMovieActingCastUseCase: FetchMovieActingCastUseCase,
    private var fetchSimilarMoviesUseCase: FetchSimilarMoviesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher

):ViewModel() {

    private val _state: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState())
    val state: StateFlow<DetailsUiState> = _state.asStateFlow()


    fun initArguments(){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    selectedMovieID = savedStateHandle.get<String>(MOVIE_ID)?.toInt()?:0
                )
            }
            onAppear()
        }
    }

    private fun onAppear(){
        getMovieDetails()
        getMovieActingTask()
        getSimilarMovies()
    }

    private fun getMovieDetails() {
        viewModelScope.launch(dispatcher) {
            val result = fetchMovieDetailsUseCase(state.value.selectedMovieID)
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
                                movieDetails = response.data?: MovieUI()
                            )
                        }
                    }
                }
            }
        }

    }

    private fun getMovieActingTask() {
        viewModelScope.launch(dispatcher) {
            val result = fetchMovieActingCastUseCase(state.value.selectedMovieID)
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
                                movieCast = response.data?.castList?: listOf()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getSimilarMovies() {
        viewModelScope.launch(dispatcher) {
            val result = fetchSimilarMoviesUseCase(state.value.selectedMovieID)
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
                                similarMovies = response.data?: listOf()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onMovieDetailsWishListClicked(movieUI: MovieUI){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    movieDetails = state.value.movieDetails.copy(
                        isWishListed = movieUI.isWishListed.not()
                    )
                )
            }
            if(!movieUI.isWishListed)
                addMovieToWishListUseCase(movieUI=movieUI)
            else
                removeMovieFromWatchListUseCase(movieUI=movieUI)
        }
    }
    fun onSimilarMovieWishListClicked(movieUI: MovieUI){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    similarMovies = state.value.similarMovies.map {
                        it.copy(
                            isWishListed = if(it.id==movieUI.id) !it.isWishListed else it.isWishListed
                        )
                    }
                )
            }
            if(!movieUI.isWishListed)
                addMovieToWishListUseCase(movieUI=movieUI)
            else
                removeMovieFromWatchListUseCase(movieUI=movieUI)
        }
    }
}