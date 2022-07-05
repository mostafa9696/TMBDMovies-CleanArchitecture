package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import javax.inject.Inject

class MovieCastDomainMapper @Inject constructor() : Mapper<MovieCastResponse, MovieCastPresentation> {

    override fun from(e: MovieCastPresentation): MovieCastResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieCastResponse): MovieCastPresentation {
        return MovieCastPresentation(
            profileImageUrl = t.profile_path,
            name = t.name,
            characterFilmName = t.character
        )
    }
}