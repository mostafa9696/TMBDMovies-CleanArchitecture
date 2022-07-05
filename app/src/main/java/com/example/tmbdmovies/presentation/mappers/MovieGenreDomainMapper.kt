package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.presentation.model.MovieGenrePresentation
import javax.inject.Inject

class MovieGenreDomainMapper @Inject constructor() : Mapper<MovieGenreResponse, MovieGenrePresentation> {

    override fun from(e: MovieGenrePresentation): MovieGenreResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieGenreResponse): MovieGenrePresentation {
        return MovieGenrePresentation(
            id = t.id,
            name = t.name
        )
    }
}