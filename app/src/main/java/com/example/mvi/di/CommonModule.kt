package com.example.mvi.di

import com.example.mvi.base.AppDispatcher
import com.example.mvi.base.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Ichvandi
 * Created on 01/06/2022 at 10:52.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {

    @Binds
    abstract fun providesAppDispatcher(appDispatcher: AppDispatcher): DispatcherProvider

}
