package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.common.NetworkConnectivityHelper
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieGenresResponse
import com.example.tmbdmovies.data.models.MovieTrailersResponse
import com.example.tmbdmovies.data.remote.MoviesRemoteDateSource
import com.example.tmbdmovies.domain.repository.BaseRepository
import com.example.tmbdmovies.domain.repository.DetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val moviesRemoteDateSource: MoviesRemoteDateSource,
    networkConnectivityHelper: NetworkConnectivityHelper,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(networkConnectivityHelper, dispatcher), DetailsRepository {

    override suspend fun getMovieCast(movieId: Long): Flow<Resource<MovieCastsResponse>> =
        networkFlowOnly(
            remoteCall = {
                moviesRemoteDateSource.getMovieCast(movieId)
            }
        )


    override suspend fun getMovieTrailers(movieID: Long): Flow<Resource<MovieTrailersResponse>> =
        networkFlowOnly(
            remoteCall = {
                moviesRemoteDateSource.getMovieTrailers(movieID)
            }
        )

    override suspend fun getTvCast(tvId: Long): Flow<Resource<MovieCastsResponse>> =
        networkFlowOnly(
            remoteCall = {
                moviesRemoteDateSource.getTvCast(tvId)
            }
        )

    override suspend fun getTvTrailers(tvID: Long): Flow<Resource<MovieTrailersResponse>> =
        networkFlowOnly(
            remoteCall = {
                moviesRemoteDateSource.getTvTrailers(tvID)
            }
        )

    override suspend fun getGenres(): Flow<Resource<MovieGenresResponse>> =
        networkFlowOnly(
            remoteCall = {
                moviesRemoteDateSource.getGenres()
            }
        )

}