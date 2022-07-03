package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.BuildConfig
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import retrofit2.http.Path

interface MoviesRepository {

    val API_KEY: String
        get() = BuildConfig.TMDB_API_KEY

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getTvSeries(page: Int): List<Movie>

    suspend fun getTopRatedMovies(page: Int): List<Movie>

    suspend fun searchMovie(query: String, page: Int): List<Movie>

    suspend fun getMovieCast(movieId: Long): List<MovieCast>

    suspend fun getMovieTrailers(movieID: Long): List<MovieTrailer>

    suspend fun getMovieGenres(): List<MovieGenre>

}