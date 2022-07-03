package com.example.tmbdmovies.data.models

data class MovieGenresResponse(
    val genres: List<MovieGenreResponse>
)

data class MovieGenreResponse(
    val id: Int,
    val name: String? = null
)
