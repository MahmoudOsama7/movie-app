package com.example.base.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Retention
@Qualifier
annotation class MainImmediateDispatcher // UI-related

@Retention
@Qualifier
annotation class IoDispatcher // I/O-related

@Retention
@Qualifier
annotation class DefaultDispatcher // CPU-related

@Module
@InstallIn(ActivityRetainedComponent::class)
object CoroutinesDispatcherModule {

    @MainImmediateDispatcher
    @ActivityRetainedScoped
    @Provides
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @IoDispatcher
    @ActivityRetainedScoped
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @ActivityRetainedScoped
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
