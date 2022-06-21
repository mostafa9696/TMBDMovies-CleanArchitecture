package com.example.tmbdmovies.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.tmbdmovies.domain.usecases.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {


    val upcomingMovies =
        getUpcomingMoviesUseCase().cachedIn(viewModelScope)

}