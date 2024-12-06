package com.example.home.di

import android.content.Context
import androidx.room.Room
import com.example.home.data.local.MOVIE_DB
import com.example.home.data.local.MovieDAO
import com.example.home.data.local.MovieDB
import com.example.home.data.remote.HomeService
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Provides
    @ActivityRetainedScoped
    fun provideMovieDao(
        database: MovieDB
    ): MovieDAO {
        return database.getMovieDAO()
    }


    @Provides
    @ActivityRetainedScoped
    fun provideMovieDB(
        @ApplicationContext applicationContext: Context
    ): MovieDB = Room.databaseBuilder(
        applicationContext,
        MovieDB::class.java,
        MOVIE_DB
    ).fallbackToDestructiveMigration()
        .build()

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