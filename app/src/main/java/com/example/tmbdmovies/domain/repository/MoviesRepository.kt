package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.BuildConfig
import com.example.tmbdmovies.data.models.Movie

interface MoviesRepository {

    val API_KEY: String
        get() = BuildConfig.TMDB_API_KEY

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun getUpcomingMovies(page: Int): List<Movie>

    suspend fun searchMovie(query: String, page: Int): List<Movie>

}