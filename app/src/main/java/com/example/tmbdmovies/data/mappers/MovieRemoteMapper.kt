package com.example.tmbdmovies.data.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.domain.models.Movie
import javax.inject.Inject

class MovieRemoteMapper @Inject constructor(): Mapper<MovieResponse, Movie> {

    override fun from(e: Movie): MovieResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieResponse): Movie {
        return Movie(
            genres = t.genres?.map { it.name },
            id = t.id,
            title = t.original_title ?: t.original_name,
            posterPath = t.poster_path,
            overview = t.overview,
            releaseDate = t.release_date,
            voteAverage = t.vote_average,
            videos = t.videos?.results?.map { it.key }
        )
    }
}