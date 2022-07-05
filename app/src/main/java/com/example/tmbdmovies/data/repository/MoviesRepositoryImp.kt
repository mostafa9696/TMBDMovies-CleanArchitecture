package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.common.NetworkConnectivityHelper
import com.example.tmbdmovies.data.models.MoviesResponse
import com.example.tmbdmovies.data.remote.MoviesRemoteDateSource
import com.example.tmbdmovies.domain.repository.BaseRepository
import com.example.tmbdmovies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val moviesRemoteDateSource: MoviesRemoteDateSource,
    networkConnectivityHelper: NetworkConnectivityHelper,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(networkConnectivityHelper, dispatcher), MoviesRepository {

    override suspend fun getPopularMovies(page: Int): MoviesResponse =
        moviesRemoteDateSource.getPopularMovies(page)


    override suspend fun getTvSeries(page: Int): MoviesResponse =
        moviesRemoteDateSource.getTvSeries(page)


    override suspend fun getTopRatedMovies(page: Int): MoviesResponse =
        moviesRemoteDateSource.getTopRatedMovies(page)


    override suspend fun searchMovie(query: String, page: Int): MoviesResponse =
        moviesRemoteDateSource.searchMovie(query, page)


}