package com.example.tmbdmovies.data.repository

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.data.network.ApisService
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import com.example.tmbdmovies.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImp @Inject constructor(
    private val apisService: ApisService,
    private val movieCastRemoteMapper: Mapper<MovieCastResponse, MovieCast>,
    private val movieTrailerRemoteMapper: Mapper<MovieTrailerResponse, MovieTrailer>,
    private val movieGenreRemoteMapper: Mapper<MovieGenreResponse, MovieGenre>
) : DetailsRepository {

    override suspend fun getMovieCast(movieId: Long): List<MovieCast> =
        apisService.getMovieCast(movieId).cast.map {
            movieCastRemoteMapper.to(it)
        }

    override suspend fun getMovieTrailers(movieID: Long): List<MovieTrailer> =
        apisService.getMovieTrailers(movieID).results.map {
            movieTrailerRemoteMapper.to(it)
        }

    override suspend fun getTvCast(tvId: Long): List<MovieCast> =
        apisService.getTvCast(tvId).cast.map {
            movieCastRemoteMapper.to(it)
        }

    override suspend fun getTvTrailers(tvID: Long): List<MovieTrailer> =
        apisService.getTvTrailers(tvID).results.map {
            movieTrailerRemoteMapper.to(it)
        }

    override suspend fun getGenres(): List<MovieGenre> =
        apisService.getMovieGenres().genres.map {
            movieGenreRemoteMapper.to(it)
        }

}