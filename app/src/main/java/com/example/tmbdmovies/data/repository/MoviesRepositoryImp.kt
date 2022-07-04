package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.data.network.ApisService
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val apisService: ApisService,
    private val movieRemoteMapper: Mapper<MovieResponse, Movie>
) : MoviesRepository {

    // todo handle api failure exceptions
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

}