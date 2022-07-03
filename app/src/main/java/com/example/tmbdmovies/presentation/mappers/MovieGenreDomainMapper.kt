package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.presentation.model.MovieGenrePresentation
import javax.inject.Inject

class MovieGenreDomainMapper @Inject constructor() : Mapper<MovieGenre, MovieGenrePresentation> {

    override fun from(e: MovieGenrePresentation): MovieGenre {
        throw Exception("Not supported")
    }

    override fun to(t: MovieGenre): MovieGenrePresentation {
        return MovieGenrePresentation(
            id = t.id,
            name = t.name
        )
    }
}