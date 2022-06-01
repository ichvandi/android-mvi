package com.example.mvi.data.repository.implementation

import com.example.mvi.base.AppDispatcher
import com.example.mvi.base.Resource
import com.example.mvi.data.remote.datasource.MovieDataSource
import com.example.mvi.data.repository.contract.IMovieRepository
import com.example.mvi.model.movie.MovieMini
import com.example.mvi.util.mapResource
import com.example.mvi.util.toMovieMiniEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dispatcher: AppDispatcher,
    private val movieDataSource: MovieDataSource
) : IMovieRepository {

    override fun getNowPlayingMovies(page: Int): Flow<Resource<List<MovieMini>>> {
        return movieDataSource.getNowPlayingMovies(page)
            .mapResource(dispatcher) { it?.results!!.map { movie -> movie.toMovieMiniEntity() } }
    }

    override fun getUpcomingMovies(page: Int): Flow<Resource<List<MovieMini>>> {
        return movieDataSource.getUpcomingMovies(page)
            .mapResource(dispatcher) { it?.results!!.map { movie -> movie.toMovieMiniEntity() } }
    }
}