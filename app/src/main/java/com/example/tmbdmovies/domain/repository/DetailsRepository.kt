package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer

interface DetailsRepository {

    suspend fun getMovieCast(movieId: Long): List<MovieCast>

    suspend fun getMovieTrailers(movieID: Long): List<MovieTrailer>

    suspend fun getTvCast(tvId: Long): List<MovieCast>

    suspend fun getTvTrailers(tvID: Long): List<MovieTrailer>

    suspend fun getGenres(): List<MovieGenre>

}