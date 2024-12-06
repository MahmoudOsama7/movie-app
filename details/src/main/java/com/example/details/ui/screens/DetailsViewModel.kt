package com.example.details.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.details.model.DetailsUiState
import com.example.details.navigation.MOVIE_ID
import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.useCase.AddMovieToWishListUseCase
import com.example.home.domain.useCase.FetchMovieActingCastUseCase
import com.example.home.domain.useCase.FetchMovieDetailsUseCase
import com.example.home.domain.useCase.RemoveMovieFromWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private var removeMovieFromWishListUseCase: RemoveMovieFromWishListUseCase,
    private var fetchMovieActingCastUseCase: FetchMovieActingCastUseCase
):ViewModel() {

    private val _state: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState())
    val state: StateFlow<DetailsUiState> = _state.asStateFlow()


    fun initArguments(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    selectedMovieID = savedStateHandle.get<String>(MOVIE_ID)?.toInt()?:0
                )
            }
            onAppear()
        }
    }

    fun onAppear(){
        getMovieDetails()
        getMovieActingTask()
    }

    private fun getMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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

    fun onWishListClicked(movieUI: MovieUI){
        viewModelScope.launch(Dispatchers.IO) {
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
                removeMovieFromWishListUseCase(movieUI=movieUI)
        }
    }
}