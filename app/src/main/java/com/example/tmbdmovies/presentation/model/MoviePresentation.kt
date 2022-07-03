package com.example.tmbdmovies.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePresentation(
    val genreIds: List<Int>? = null,
    val id: Long? = null,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val videos: List<String>? = null,
    var movieCast: List<MovieCastPresentation>? = null,
    var movieTrailers: List<MovieTrailerPresentation>? = null,
    var movieGenres: List<String>? = null
): Parcelable