package com.example.mvi.data.repository.contract

import com.example.mvi.base.Resource
import com.example.mvi.model.movie.MovieMini
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getNowPlayingMovies(page: Int): Flow<Resource<List<MovieMini>>>

    fun getUpcomingMovies(page: Int): Flow<Resource<List<MovieMini>>>

}