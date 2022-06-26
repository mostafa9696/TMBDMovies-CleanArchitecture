package com.example.tmbdmovies.data.models

data class MovieCastResponse(
     val cast: List<MovieCast>
)

data class MovieCast(
     val profile_path: String,
     val name: String,
     val character: String,
     val id: Long,
     val credit_id: String
)
