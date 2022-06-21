package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.data.models.Movie
import com.example.tmbdmovies.data.network.ApisService
import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(private val apisService: ApisService) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> =
        apisService.getPopularMovies(API_KEY, page).results

    override suspend fun getTopRatedMovies(page: Int): List<Movie> =
        apisService.getTopRatedMovies(API_KEY, page).results


    override suspend fun getUpcomingMovies(page: Int): List<Movie> =
        apisService.getUpcomingMovies(page = page).results


    override suspend fun searchMovie(query: String, page: Int): List<Movie> =
        apisService.searchMovie(API_KEY, query, page).results
}