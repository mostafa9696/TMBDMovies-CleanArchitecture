package com.example.tmbdmovies.data.models

data class MoviesResponse(
    val results: List<MovieResponse>
)

data class MovieResponse(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>? = null,
    val id: Long? = null,
    val original_title: String? = null, // for movie case
    val original_name: String? = null, // for series case
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val vote_average: Double? = null,
    val videos: VideoResult? = null,
) {

    data class VideoResult(
        val results: List<Result>
    ) {
        data class Result(
            val id: String,
            val key: String,
            val site: String,
            val type: String
        )
    }
}

