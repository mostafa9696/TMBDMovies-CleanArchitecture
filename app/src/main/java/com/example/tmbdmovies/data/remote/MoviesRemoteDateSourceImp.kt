package com.example.tmbdmovies.data.remote

import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieGenresResponse
import com.example.tmbdmovies.data.models.MovieTrailersResponse
import com.example.tmbdmovies.data.models.MoviesResponse
import javax.inject.Inject

class MoviesRemoteDateSourceImp @Inject constructor(
    private val apisService: ApisService,
) : BaseRemoteDataSource(), MoviesRemoteDateSource {

    override suspend fun getPopularMovies(page: Int): MoviesResponse =
        makeRequest { apisService.getPopularMovies(page) }


    override suspend fun getTvSeries(page: Int): MoviesResponse =
        makeRequest { apisService.getTvShows(page) }

    override suspend fun getTopRatedMovies(page: Int): MoviesResponse =
        makeRequest { apisService.getTopRatedMovies(page) }

    override suspend fun getMovieCast(movieId: Long): MovieCastsResponse =
        makeRequest { apisService.getMovieCast(movieId) }

    override suspend fun getMovieTrailers(movieID: Long): MovieTrailersResponse =
        makeRequest { apisService.getMovieTrailers(movieID) }

    override suspend fun getTvCast(tvId: Long): MovieCastsResponse =
        makeRequest { apisService.getTvCast(tvId) }

    override suspend fun getTvTrailers(tvID: Long): MovieTrailersResponse =
        makeRequest { apisService.getTvTrailers(tvID) }

    override suspend fun getGenres(): MovieGenresResponse =
        makeRequest { apisService.getMovieGenres() }

}