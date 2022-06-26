package com.example.tmbdmovies.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.usecases.GetMoviesUseCase
import com.example.tmbdmovies.presentation.model.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val mapper: Mapper<Movie, MoviePresentation>
) : ViewModel() {

    private val requestStateFlow = MutableStateFlow<String?>(null)

    var movies: Flow<PagingData<MoviePresentation>> = requestStateFlow
       //.debounce(300)
        .filterNotNull()
        .filterNot {
            it.isEmpty()
        }
        .flatMapLatest {
            getMoviesUseCase(it)
        }
        .map { pagingData ->
            pagingData.map { mapper.to(it) }
        }
        .cachedIn(viewModelScope)

    fun getMovies(query: String) {
      /*  upcomingMovies = flow {
            getMoviesUseCase(query).cachedIn(viewModelScope)
        }*/

        //upcomingMovies = getMoviesUseCase(query).cachedIn(viewModelScope)
        requestStateFlow.value = query
    }
}