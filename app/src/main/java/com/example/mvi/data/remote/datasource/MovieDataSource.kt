package com.example.mvi.data.remote.datasource

import com.example.mvi.util.ResourcePagingFlow
import com.example.mvi.util.networkCall
import com.example.mvi.data.remote.response.MovieMiniResponse
import com.example.mvi.data.remote.service.MovieService
import javax.inject.Inject

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 23:18.
 */
class MovieDataSource @Inject constructor(private val service: MovieService) {

    fun getNowPlayingMovies(page: Int): ResourcePagingFlow<MovieMiniResponse> {
        return networkCall { service.getNowPlayingMovies(page) }
    }

    fun getUpcomingMovies(page: Int): ResourcePagingFlow<MovieMiniResponse> {
        return networkCall { service.getUpcomingMovies(page) }
    }

}