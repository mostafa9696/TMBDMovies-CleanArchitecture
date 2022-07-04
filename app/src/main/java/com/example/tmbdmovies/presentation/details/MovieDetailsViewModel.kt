package com.example.tmbdmovies.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import com.example.tmbdmovies.domain.usecases.GetMovieCastUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieGenresUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieTrailersUseCase
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import com.example.tmbdmovies.presentation.model.MovieGenrePresentation
import com.example.tmbdmovies.presentation.model.MoviePresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase,
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val movieCastMapper: Mapper<MovieCast, MovieCastPresentation>,
    private val movieTrailerMapper: Mapper<MovieTrailer, MovieTrailerPresentation>,
    private val movieGenreMapper: Mapper<MovieGenre, MovieGenrePresentation>
) : ViewModel() {


    private val _movieCastStateFlow =
        MutableStateFlow<Resource<MoviePresentation?>>(Resource.Loading)

    val movieCastStateFlow: StateFlow<Resource<MoviePresentation?>> =
        _movieCastStateFlow

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        // liveData.postValue(ViewState.Error(throwable))
        viewModelScope.launch {
            _movieCastStateFlow.emit(Resource.Error(throwable))
        }
    }

    fun getMovieInfo(moviePresentation: MoviePresentation) {
        // if we handle exception with only try&catch, the app will crash
        // because the crash happened in lunch or async scope and no Exception handler exist
        // so we will replace try,catch with CoroutineExceptionHandler
        viewModelScope.launch(exceptionHandler) {
            //   try {
            val cast = async {
                getMovieCastUseCase(moviePresentation.id!!, moviePresentation.type).map {
                    movieCastMapper.to(it)
                }
            }
            val trailers = async {
                getMovieTrailersUseCase(moviePresentation.id!!, moviePresentation.type).map {
                    movieTrailerMapper.to(it)
                }
            }
            val allGenres = async {
                getMovieGenresUseCase().map {
                    movieGenreMapper.to(it)
                }
            }
            moviePresentation.movieCast = cast.await()
            moviePresentation.movieTrailers = trailers.await()
            moviePresentation.movieGenres =
                getMovieGenres(allGenres.await(), moviePresentation.genreIds)
            _movieCastStateFlow.emit(Resource.Success(moviePresentation))
            /*    } catch (e: HttpException) {
                    _movieCastStateFlow.emit(Resource.Error(e))
                }*/
        }
    }

    private fun getMovieGenres(
        allGenres: List<MovieGenrePresentation>,
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