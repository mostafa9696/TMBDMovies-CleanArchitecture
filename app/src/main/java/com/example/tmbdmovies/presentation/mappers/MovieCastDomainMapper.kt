package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import javax.inject.Inject

class MovieCastDomainMapper @Inject constructor() : Mapper<MovieCast, MovieCastPresentation> {

    override fun from(e: MovieCastPresentation): MovieCast {
        throw Exception("Not supported")
    }

    override fun to(t: MovieCast): MovieCastPresentation {
        return MovieCastPresentation(
            profileImageUrl = t.profileImageUrl,
            name = t.name,
            characterFilmName = t.character
        )
    }
}