package com.example.mvi.util

import com.example.mvi.data.remote.response.MovieMiniResponse
import com.example.mvi.model.movie.Image
import com.example.mvi.model.movie.MovieMini
import com.example.mvi.model.movie.Title

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 23:34.
 */


fun MovieMiniResponse.toMovieMiniEntity() = MovieMini(
    id = id,
    title = Title(title, originalTitle),
    image = Image(poster, backdrop),
    overview = overview,
    releaseDate = releaseDate,
    genreIds = genreIds,
    originalLanguage = language,
    popularity = popularity,
    voteCount = voteCount,
    voteAverage = voteAverage,
    isAdult = isAdult,
    hasVideo = hasVideo
)
