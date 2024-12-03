package com.example.home.ui

import androidx.lifecycle.ViewModel
import com.example.home.domain.useCase.FetchDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private var fetchDataUseCase: FetchDataUseCase
):ViewModel() {

}