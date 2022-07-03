package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.data.network.ApisService
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val apisService: ApisService,
    private val movieRemoteMapper: Mapper<MovieResponse, Movie>,
    private val movieCastRemoteMapper: Mapper<MovieCastResponse, MovieCast>,
    private val movieTrailerRemoteMapper: Mapper<MovieTrailerResponse, MovieTrailer>,
    private val movieGenreRemoteMapper: Mapper<MovieGenreResponse, MovieGenre>
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> =
        apisService.getPopularMovies(page).results.map {
            movieRemoteMapper.to(it)
        }

    override suspend fun getTvSeries(page: Int): List<Movie> =
        apisService.getTvShows(page).results.map {
            movieRemoteMapper.to(it)
        }


    override suspend fun getTopRatedMovies(page: Int): List<Movie> =
        apisService.getTopRatedMovies(page = page).results.map {
            movieRemoteMapper.to(it)
        }


    override suspend fun searchMovie(query: String, page: Int): List<Movie> =
        apisService.searchMovie(query, page).results.map {
            movieRemoteMapper.to(it)
        }

    override suspend fun getMovieCast(movieId: Long): List<MovieCast> =
        apisService.getMovieCast(movieId).cast.map {
            movieCastRemoteMapper.to(it)
        }

    override suspend fun getMovieTrailers(movieID: Long): List<MovieTrailer> =
        apisService.getMovieTrailers(movieID).results.map {
            movieTrailerRemoteMapper.to(it)
        }

    override suspend fun getMovieGenres(): List<MovieGenre> =
        apisService.getMovieGenres().genres.map {
            movieGenreRemoteMapper.to(it)
        }

}