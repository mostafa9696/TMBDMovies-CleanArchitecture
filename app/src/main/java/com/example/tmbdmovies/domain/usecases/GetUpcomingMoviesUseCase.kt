package com.example.tmbdmovies.domain.usecases

import androidx.paging.Pager
import com.example.tmbdmovies.data.MoviesPagingSource
import com.example.tmbdmovies.domain.repository.MoviesRepository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val repository: MoviesRepository) : BaseUseCase() {

    operator fun invoke() = Pager(pagingConfig) {
        MoviesPagingSource(repository, MoviesPagingSource.UPCOMING)
    }.flow
}