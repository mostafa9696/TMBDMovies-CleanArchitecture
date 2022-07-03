package com.example.tmbdmovies.data.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.domain.models.MovieCast
import javax.inject.Inject

class MovieCastsRemoteMapper @Inject constructor() : Mapper<MovieCastResponse, MovieCast> {

    override fun from(e: MovieCast): MovieCastResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieCastResponse): MovieCast {
        return MovieCast(
            profileImageUrl = t.profile_path,
            name = t.name,
            character = t.character
        )
    }
}