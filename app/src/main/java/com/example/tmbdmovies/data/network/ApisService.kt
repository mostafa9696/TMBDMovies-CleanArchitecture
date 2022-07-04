package com.example.tmbdmovies.data.network

import com.example.tmbdmovies.BuildConfig
import com.example.tmbdmovies.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApisService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("page")
        page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse


    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id")
        movieId: Long,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
        ): MovieTrailersResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTvTrailers(
        @Path("tv_id")
        tvId: Long,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): MovieTrailersResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): MovieGenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Long,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MovieCastsResponse


    @GET("tv/{tv_id}/credits")
    suspend fun getTvCast(
        @Path("tv_id") tvId: Long,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MovieCastsResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") clientId: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

}