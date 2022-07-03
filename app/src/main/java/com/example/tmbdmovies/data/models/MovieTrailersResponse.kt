package com.example.tmbdmovies.data.models

data class MovieTrailersResponse(
    val results: List<MovieTrailerResponse>
)

data class MovieTrailerResponse(
    val name: String? = null,
    val key: String? = null,
    val official: Boolean? = null
)
