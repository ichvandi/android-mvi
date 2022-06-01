package com.example.mvi.ui.home

import com.airbnb.epoxy.EpoxyController
import com.example.mvi.model.epoxy.ItemMovieModel
import com.example.mvi.model.epoxy.ItemTitleModel
import com.example.mvi.model.movie.MovieMini
import com.example.mvi.util.epoxy.carouselBuilder

/**
 * @author Ichvandi
 * Created on 01/06/2022 at 13:25.
 */
class MovieController : EpoxyController() {

    private val nowPlayingMovies = mutableListOf<MovieMini>()
    private val upcomingMovies = mutableListOf<MovieMini>()

    fun setNowPlayingMovies(movies: List<MovieMini>) {
        nowPlayingMovies.addAll(movies)
        requestModelBuild()
    }

    fun setUpcomingMovies(movies: List<MovieMini>) {
        upcomingMovies.addAll(movies)
        requestModelBuild()
    }

    override fun buildModels() {
        if (nowPlayingMovies.isNotEmpty()) {
            ItemTitleModel("Now Playing Movies")
                .id("title_now_playing")
                .addTo(this)

            carouselBuilder {
                id("now_playing_movies")
                for (i in this@MovieController.nowPlayingMovies) {
                    add(
                        ItemMovieModel(i.title.original, i.image.poster.orEmpty()) {

                        }.id(i.id)
                    )
                }
            }
        }

        if (upcomingMovies.isNotEmpty()) {
            ItemTitleModel("Upcoming Movies")
                .id("title_upcoming")
                .addTo(this)

            carouselBuilder {
                id("upcoming_movies")
                for (i in this@MovieController.upcomingMovies) {
                    add(
                        ItemMovieModel(i.title.original, i.image.poster.orEmpty()) {

                        }.id(i.id)
                    )
                }
            }
        }
    }

}