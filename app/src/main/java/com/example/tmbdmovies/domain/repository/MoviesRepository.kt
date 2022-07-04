package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.domain.models.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTvSeries(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun searchMovie(query: String, page: Int): List<Movie>

}