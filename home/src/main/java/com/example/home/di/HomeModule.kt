package com.example.home.di

import com.example.home.data.remote.HomeService
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeProvidesModule {
    @Provides
    @ActivityRetainedScoped
    fun HomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Module
    @InstallIn(ViewModelComponent::class)
    interface HomeBindModule {
        @Binds
        @ViewModelScoped
        fun bindHomeRepository(
            additionalInfoRepository: HomeRepositoryImpl
        ): HomeRepository
    }
}