package com.example.tmbdmovies.presentation.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import com.example.tmbdmovies.presentation.model.MovieGenrePresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import javax.inject.Inject

class MovieTrailerDomainMapper @Inject constructor() : Mapper<MovieTrailer, MovieTrailerPresentation> {

    override fun from(e: MovieTrailerPresentation): MovieTrailer {
        throw Exception("Not supported")
    }

    override fun to(t: MovieTrailer): MovieTrailerPresentation {
        return MovieTrailerPresentation(
            key = t.key,
            name = t.name,
            official = t.official
        )
    }
}