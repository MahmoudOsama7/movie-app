package com.example.feature.home.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.base.dispatchers.IoDispatcher
import com.example.base.pager.RequestLoadingStateListener
import com.example.base.pager.createPager
import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.useCase.AddMovieToWishListUseCase
import com.example.movie_data.domain.useCase.FetchMoviesUseCase
import com.example.movie_data.domain.useCase.FetchTheFirstTenPopularMoviesUseCase
import com.example.movie_data.domain.useCase.FetchCachedPaginatedMoviesUseCase
import com.example.movie_data.domain.useCase.RemoveMovieFromWatchListUseCase
import com.example.feature.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
    private var removeMovieFromWatchListUseCase: RemoveMovieFromWatchListUseCase,
    private var fetchMoviesUseCase: FetchMoviesUseCase,
    private var fetchCachedPaginatedMoviesUseCase: FetchCachedPaginatedMoviesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(), RequestLoadingStateListener {

    private val _state: MutableStateFlow<HomeUIState> =
        MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state.asStateFlow()


    fun onAppear() {
        viewModelScope.launch(dispatcher) {
            getPopularMovies()
            getMovies()
        }
    }

    private fun getMovies(){
        _state.update {
            it.copy(
                moviesList = createPager(
                    requestLoadingStateListener = this@HomeScreenViewModel,
                    fetchData = { page ->
                        fetchMoviesUseCase(
                            page=page,
                            year = 2024
                        )
                    },
                    fetchCachedData = {page->
                        fetchCachedPaginatedMoviesUseCase(page=page)
                    }
                ).flow.cachedIn(viewModelScope)
            )
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
                            popularMoviesList = response.data?: listOf(),
                            showLoading = false
                        )
                    }
                }

                response.isSuccess() -> {
                    _state.update {
                        it.copy(
                            showLoading = false,
                            popularMoviesList = response.data ?: listOf()
                        )
                    }
                }
            }
        }
    }

    fun onFavouriteClicked(movieUI: MovieUI){
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    popularMoviesList = state.value.popularMoviesList.map {
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

    override suspend fun onLoading() {
    }

    override suspend fun onFinishLoading() {
    }

    override suspend fun onError(error: String) {
    }
}