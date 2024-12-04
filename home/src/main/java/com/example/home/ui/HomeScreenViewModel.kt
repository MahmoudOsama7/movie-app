package com.example.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.useCase.FetchDataUseCase
import com.example.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private var fetchDataUseCase: FetchDataUseCase
):ViewModel() {

    private val _state: MutableStateFlow<HomeUIState> =
        MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state.asStateFlow()

    fun getPopularMovies(){
        viewModelScope.launch {
            val result = fetchDataUseCase()
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
                                moviesList = response.data?: listOf()
                            )
                        }
                    }
                }
            }
        }
    }
}