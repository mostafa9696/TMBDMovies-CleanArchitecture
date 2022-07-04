package com.example.tmbdmovies.domain.usecases

import com.example.tmbdmovies.domain.repository.DetailsRepository
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(private val repository: DetailsRepository) :
    BaseUseCase() {

    suspend operator fun invoke() = repository.getGenres()
}