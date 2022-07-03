package com.example.tmbdmovies.domain.usecases

import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieTrailersUseCase @Inject constructor(private val repository: MoviesRepository) :
    BaseUseCase() {

    suspend operator fun invoke(movieID: Long) = repository.getMovieTrailers(movieID)
}