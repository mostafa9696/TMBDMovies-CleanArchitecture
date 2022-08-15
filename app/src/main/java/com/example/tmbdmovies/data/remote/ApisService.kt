package com.example.tmbdmovies.data.remote

import com.example.tmbdmovies.BuildConfig
import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieGenresResponse
import com.example.tmbdmovies.data.models.MovieTrailersResponse
import com.example.tmbdmovies.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApisService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): Response<MoviesResponse>

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("page")
        page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): Response<MoviesResponse>


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): Response<MoviesResponse>


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id")
        movieId: Long,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieTrailersResponse>

    @GET("tv/{tv_id}/videos")
    suspend fun getTvTrailers(
        @Path("tv_id")
        tvId: Long,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieTrailersResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieGenresResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Long,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieCastsResponse>


    @GET("tv/{tv_id}/credits")
    suspend fun getTvCast(
        @Path("tv_id") tvId: Long,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieCastsResponse>

}