package com.example.tmbdmovies.domain.usecases

import com.example.tmbdmovies.data.MoviesPagingSource
import com.example.tmbdmovies.domain.repository.DetailsRepository
import javax.inject.Inject

class GetMovieTrailersUseCase @Inject constructor(private val repository: DetailsRepository) :
    BaseUseCase() {

    suspend operator fun invoke(id: Long, type: String) =
        if (type == MoviesPagingSource.TV_SERIES) repository.getTvTrailers(id)
        else repository.getMovieTrailers(id)
}