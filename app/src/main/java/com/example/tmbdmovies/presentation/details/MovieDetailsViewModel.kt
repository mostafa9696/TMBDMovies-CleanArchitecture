package com.example.tmbdmovies.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.domain.usecases.GetMovieCastUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieGenresUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieTrailersUseCase
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val movieCastMapper: Mapper<MovieCastResponse, MovieCastPresentation>,
    private val movieTrailerMapper: Mapper<MovieTrailerResponse, MovieTrailerPresentation>
) : ViewModel() {


    private val _movieCastStateFlow =
        MutableStateFlow<Resource<List<MovieCastPresentation>>>(Resource.Loading)
    val movieCastStateFlow: StateFlow<Resource<List<MovieCastPresentation>>> =
        _movieCastStateFlow

    private val _movieGenreStateFlow =
        MutableStateFlow<Resource<List<String>>>(Resource.Loading)
    val movieGenreStateFlow: StateFlow<Resource<List<String>>> =
        _movieGenreStateFlow

    private val _movieTrailersStateFlow =
        MutableStateFlow<Resource<List<MovieTrailerPresentation>>>(Resource.Loading)
    val movieTrailersStateFlow: StateFlow<Resource<List<MovieTrailerPresentation>>> =
        _movieTrailersStateFlow


    fun getMovieCast(movieID: Long, type: String) {
        viewModelScope.launch {
            getMovieCastUseCase(movieID, type).collect {
                when (it) {
                    is Resource.Loading -> _movieCastStateFlow.emit(Resource.Loading)
                    is Resource.Error -> _movieCastStateFlow.emit(Resource.Error(it.exception))
                    is Resource.Success -> _movieCastStateFlow.emit(
                        Resource.Success
                            (it.data?.cast?.map { castResponse ->
                            movieCastMapper.to(castResponse)
                        })
                    )
                }
            }
        }
    }

    fun getMovieTrailers(movieID: Long, type: String) {
        viewModelScope.launch {
            getMovieTrailersUseCase(movieID, type).collect {
                when (it) {
                    is Resource.Loading -> _movieTrailersStateFlow.emit(Resource.Loading)
                    is Resource.Error -> _movieTrailersStateFlow.emit(Resource.Error(it.exception))
                    is Resource.Success -> _movieTrailersStateFlow.emit(
                        Resource.Success
                            (it.data?.results?.map { trailerResponse ->
                            movieTrailerMapper.to(trailerResponse)
                        })
                    )
                }
            }
        }
    }

    fun getMovieGenre(movieGenreIDs: List<Int>?) {
        viewModelScope.launch {
            getMovieGenresUseCase().collect {
                when (it) {
                    is Resource.Loading -> _movieGenreStateFlow.emit(Resource.Loading)
                    is Resource.Error -> _movieGenreStateFlow.emit(Resource.Error(it.exception))
                    is Resource.Success -> {
                        val movieGenres = getMovieGenres(it.data!!.genres, movieGenreIDs)
                        _movieGenreStateFlow.emit(
                            Resource.Success(movieGenres)
                        )
                    }
                }
            }
        }
    }

    private fun getMovieGenres(
        allGenres: List<MovieGenreResponse>,
        genreIds: List<Int>?
    ): List<String> {
        val movieGenres = mutableListOf<String>()
        genreIds?.forEach { genreID ->
            movieGenres.add(
                allGenres.find { it.id == genreID }?.name ?: ""
            )
        }
        return movieGenres
    }
}