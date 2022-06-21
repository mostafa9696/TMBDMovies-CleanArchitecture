package com.example.tmbdmovies.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmbdmovies.data.models.Movie
import com.example.tmbdmovies.domain.repository.MoviesRepository

class MoviesPagingSource(
    private val moviesRepository: MoviesRepository,
    private val query: String
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val page = params.key ?: 1

        return try {
            when (query) {

                TOP_RATED -> {
                    val popularMovies = moviesRepository.getTopRatedMovies(page)
                    LoadResult.Page(
                        data = popularMovies,
                        prevKey = null,
                        nextKey = if (popularMovies.isEmpty()) null else page + 1
                    )
                }
                POPULAR -> {
                    val popularMovies = moviesRepository.getPopularMovies(page)
                    LoadResult.Page(
                        data = popularMovies,
                        prevKey = null,
                        nextKey = if (popularMovies.isEmpty()) null else page + 1
                    )
                }
                UPCOMING -> {
                    val popularMovies = moviesRepository.getUpcomingMovies(page)
                    LoadResult.Page(
                        data = popularMovies,
                        prevKey = null,
                        nextKey = if (popularMovies.isEmpty()) null else page + 1
                    )
                }
                else -> {
                    val popularMovies = moviesRepository.searchMovie(query, page)
                    LoadResult.Page(
                        data = popularMovies,
                        prevKey = null,
                        nextKey = if (popularMovies.isEmpty()) null else page + 1
                    )
                }
            }
        } catch (ex: Exception) {
            Log.d("MoviesPagingSource", "load: ${ex.message}")
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }
}