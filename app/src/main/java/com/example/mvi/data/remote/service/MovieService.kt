package com.example.mvi.data.remote.service

import com.example.mvi.util.ResponsePaging
import com.example.mvi.data.remote.response.MovieMiniResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 22:54.
 */
interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): ResponsePaging<MovieMiniResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): ResponsePaging<MovieMiniResponse>

}