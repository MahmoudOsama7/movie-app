package com.example.home.domain.useCase

import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FetchDataUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    suspend operator fun invoke(referralCode: String) = ""
}