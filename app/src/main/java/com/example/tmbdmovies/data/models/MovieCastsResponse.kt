package com.example.tmbdmovies.data.models

data class MovieCastsResponse(
     val cast: List<MovieCastResponse>
)

data class MovieCastResponse(
     val profile_path: String? = null,
     val name: String? = null,
     val character: String? = null,
     val id: Long? = null,
     val credit_id: String? = null
)
