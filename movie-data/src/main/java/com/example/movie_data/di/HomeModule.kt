package com.example.movie_data.di

import android.content.Context
import androidx.room.Room
import com.example.movie_data.data.local.MOVIE_DB
import com.example.movie_data.data.local.MovieDAO
import com.example.movie_data.data.local.MovieDB
import com.example.home.data.remote.MovieService
import com.example.home.data.repository.MovieRepositoryImpl
import com.example.home.domain.repository.MovieRepository
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
object MovieProvidesModule {
    @Provides
    @ActivityRetainedScoped
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)


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
    interface movieBindModule {
        @Binds
        @ViewModelScoped
        fun bindMovieRepository(
            movieRepository: MovieRepositoryImpl
        ): MovieRepository
    }


}