package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.data.models.MoviesResponse

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): MoviesResponse

    suspend fun getTvSeries(page: Int): MoviesResponse

    suspend fun getTopRatedMovies(page: Int): MoviesResponse

    suspend fun searchMovie(query: String, page: Int): MoviesResponse

}