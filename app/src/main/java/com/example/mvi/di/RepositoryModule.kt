package com.example.mvi.di

import com.example.mvi.data.repository.contract.IMovieRepository
import com.example.mvi.data.repository.implementation.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Ichvandi
 * Created on 01/06/2022 at 11:03.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMovieRepository(movieRepository: MovieRepository): IMovieRepository

}