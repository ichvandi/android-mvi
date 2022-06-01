package com.example.mvi.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieMiniResponse(

    val id: Int,

    val title: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("backdrop_path")
    val backdrop: String?,

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("original_language")
    val language: String,

    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("adult")
    val isAdult: Boolean,

    @SerializedName("video")
    val hasVideo: Boolean

)