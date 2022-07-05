package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieGenresResponse
import com.example.tmbdmovies.data.models.MovieTrailersResponse
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun getMovieCast(movieId: Long): Flow<Resource<MovieCastsResponse>>

    suspend fun getMovieTrailers(movieID: Long): Flow<Resource<MovieTrailersResponse>>

    suspend fun getTvCast(tvId: Long): Flow<Resource<MovieCastsResponse>>

    suspend fun getTvTrailers(tvID: Long): Flow<Resource<MovieTrailersResponse>>

    suspend fun getGenres(): Flow<Resource<MovieGenresResponse>>

}