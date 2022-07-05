package com.example.tmbdmovies.data.remote

import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieGenresResponse
import com.example.tmbdmovies.data.models.MovieTrailersResponse
import com.example.tmbdmovies.data.models.MoviesResponse
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer

interface MoviesRemoteDateSource {

    suspend fun getPopularMovies(page: Int): MoviesResponse

    suspend fun getTvSeries(page: Int): MoviesResponse

    suspend fun getTopRatedMovies(page: Int): MoviesResponse

    suspend fun searchMovie(query: String, page: Int): MoviesResponse

    suspend fun getMovieCast(movieId: Long): MovieCastsResponse

    suspend fun getMovieTrailers(movieID: Long): MovieTrailersResponse

    suspend fun getTvCast(tvId: Long): MovieCastsResponse

    suspend fun getTvTrailers(tvID: Long): MovieTrailersResponse

    suspend fun getGenres(): MovieGenresResponse
}