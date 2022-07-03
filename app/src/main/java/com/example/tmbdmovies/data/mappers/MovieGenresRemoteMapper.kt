package com.example.tmbdmovies.data.mappers

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.domain.models.MovieGenre
import javax.inject.Inject

class MovieGenresRemoteMapper @Inject constructor() : Mapper<MovieGenreResponse, MovieGenre> {

    override fun from(e: MovieGenre): MovieGenreResponse {
        throw Exception("Not supported")
    }

    override fun to(t: MovieGenreResponse): MovieGenre {
        return MovieGenre(
            id = t.id,
            name = t.name
        )
    }
}