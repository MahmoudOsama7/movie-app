package com.example.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    fun providesRetrofit(
        gson: Gson,
        apiURL: String,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
    @Provides
    fun provideLoggingInterceptor():OkHttpClient{
        return  OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {level = HttpLoggingInterceptor.Level.BODY})
            .build()
    }


    @Provides
    fun providesAPIURL(): String =  "https://api.themoviedb.org/3/"
}