package com.example.tmbdmovies.data.network

import com.example.tmbdmovies.data.models.Movie
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApisService {

    companion object {
        const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYzUxYjEyZGU1YWNjMjQ3ZWE5YjJmOTI2ZGEyNzFmNiIsInN1YiI6IjVmZGRmM2I3YjBiYTdlMDA0MDllYTViMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.O1SGsgJ_P2woqZJ0HWlXNtJ81mtjO-kl3ix8K-b_PS8"
    }
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): MovieResponse

    @Headers("Authorization: Bearer $ACCESS_TOKEN")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page")
        page: Int
    ): MovieResponse


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Query("api_key") api_key: String,
        @Path("movie_id")
        movieId: Long,
        @Query("append_to_response")
        appendToResponse: String
    ): Movie

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Query("api_key") api_key: String,
        @Path("movie_id") movieId: Long,
    ): MovieCastResponse


    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse

}