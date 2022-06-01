package com.example.mvi.model.movie

data class Movie(
    val id: Id,
    val title: Title,
    val image: Image,
    val overview: String?,
    val collections: List<Collection>,
    val budget: Int,
    val releaseDate: String,
    val genreIds: List<Genre>,
    val homepage: String?,
    val companies: List<Company>,
    val countries: List<Country>,
    val revenue: Int,
    val runtime: Int?,
    val originalLanguage: String,
    val languages: List<Language>,
    val tagline: String?,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double,
    val status: Status,
    val isAdult: Boolean,
    val hasVideo: Boolean
) {
    data class Id(
        val tmdb: Int,
        val imdb: String?
    )

    data class Collection(
        val id: Int,
        val name: String,
        val overview: String?,
        val image: Image,
        val parts: List<MovieMini>
    )

    enum class Status(val value: String) {
        RUMORED("Rumored"),
        PLANNED("Planned"),
        IN_PRODUCTION("In Production"),
        POST_PRODUCTION("Post Production"),
        RELEASED("Released"),
        CANCELED("Canceled")
    }
}

data class MovieMini(
    val id: Int,
    val title: Title,
    val image: Image,
    val overview: String?,
    val releaseDate: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double,
    val isAdult: Boolean,
    val hasVideo: Boolean
)
