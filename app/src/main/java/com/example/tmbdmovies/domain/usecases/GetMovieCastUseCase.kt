package com.example.tmbdmovies.domain.usecases

import com.example.tmbdmovies.data.MoviesPagingSource.Companion.TV_SERIES
import com.example.tmbdmovies.domain.repository.DetailsRepository
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(private val repository: DetailsRepository) :
    BaseUseCase() {

    suspend operator fun invoke(id: Long, type: String) =
        if (type == TV_SERIES) repository.getTvCast(id)
        else repository.getMovieCast(id)
}