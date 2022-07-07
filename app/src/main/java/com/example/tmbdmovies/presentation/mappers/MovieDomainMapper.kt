package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.presentation.model.MoviePresentation
import javax.inject.Inject

class MovieDomainMapper @Inject constructor() : Mapper<Movie, MoviePresentation> {

    override fun to(t: Movie): MoviePresentation {
        return MoviePresentation(
            genreIds = t.genreIds,
            id = t.id,
            title = t.title,
            posterPath = t.posterPath,
            backdropPath = t.backdropPath,
            overview = t.overview,
            releaseDate = t.releaseDate,
            voteAverage = t.voteAverage,
            videos = t.videos
        )
    }
}