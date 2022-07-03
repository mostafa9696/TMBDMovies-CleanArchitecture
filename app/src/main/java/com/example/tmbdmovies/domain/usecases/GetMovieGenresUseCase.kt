package com.example.tmbdmovies.domain.usecases

import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(private val repository: MoviesRepository) :
    BaseUseCase() {

    suspend operator fun invoke() = repository.getMovieGenres()
}