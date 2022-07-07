package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.presentation.model.MoviePresentation
import javax.inject.Inject

class MovieRemoteMapper @Inject constructor(): Mapper<MovieResponse, MoviePresentation> {

    override fun to(t: MovieResponse): MoviePresentation {
        return MoviePresentation(
            genreIds = t.genre_ids,
            id = t.id,
            title = t.original_title ?: t.original_name,
            posterPath = t.poster_path,
            backdropPath = t.backdrop_path,
            overview = t.overview,
            releaseDate = t.release_date,
            voteAverage = t.vote_average,
            videos = t.videos?.results?.map { it.key }
        )
    }
}