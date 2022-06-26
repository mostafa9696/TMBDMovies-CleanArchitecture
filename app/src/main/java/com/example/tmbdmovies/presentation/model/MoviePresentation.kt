package com.example.tmbdmovies.presentation.model

data class MoviePresentation(
    val genres: List<String>? = null,
    val id: Long? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val videos: List<String>? = null
)