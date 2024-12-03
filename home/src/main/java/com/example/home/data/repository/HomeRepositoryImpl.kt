package com.example.home.data.repository

import com.example.home.data.remote.HomeService
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) :HomeRepository {
}