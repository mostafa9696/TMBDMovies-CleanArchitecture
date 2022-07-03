package com.example.tmbdmovies.data.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieTrailer
import javax.inject.Inject

class MovieTrailerRemoteMapper @Inject constructor() : Mapper<MovieTrailerResponse, MovieTrailer> {

    override fun from(e: MovieTrailer): MovieTrailerResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieTrailerResponse): MovieTrailer {
        return MovieTrailer(
            name = t.name,
            key = t.key,
            official = t.official
        )
    }
}