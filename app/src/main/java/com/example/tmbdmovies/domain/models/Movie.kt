package com.example.tmbdmovies.domain.models

data class Movie(
    val genreIds: List<Int>? = null,
    val id: Long? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val videos: List<String>? = null
)