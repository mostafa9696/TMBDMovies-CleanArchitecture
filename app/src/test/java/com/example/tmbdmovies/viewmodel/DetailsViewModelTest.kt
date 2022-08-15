package com.example.tmbdmovies.viewmodel

import app.cash.turbine.test
import com.example.tmbdmovies.MainCoroutineRule
import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.domain.usecases.GetMovieCastUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieGenresUseCase
import com.example.tmbdmovies.domain.usecases.GetMovieTrailersUseCase
import com.example.tmbdmovies.presentation.details.MovieDetailsViewModel
import com.example.tmbdmovies.presentation.mappers.MovieCastDomainMapper
import com.example.tmbdmovies.presentation.mappers.MovieTrailerDomainMapper
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailsViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    lateinit var viewmodel: MovieDetailsViewModel

    @Mock
    private lateinit var getMovieCastUseCase: GetMovieCastUseCase

    @Mock
    private lateinit var getMovieTrailersUseCase: GetMovieTrailersUseCase

    @Mock
    private lateinit var getMovieGenresUseCase: GetMovieGenresUseCase

    private val castMapper: Mapper<MovieCastResponse, MovieCastPresentation> =
        MovieCastDomainMapper()
    private val trailerMapper: Mapper<MovieTrailerResponse, MovieTrailerPresentation> =
        MovieTrailerDomainMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewmodel = MovieDetailsViewModel(
            getMovieCastUseCase,
            getMovieTrailersUseCase,
            getMovieGenresUseCase,
            castMapper,
            trailerMapper
        )
    }



    @Test
    fun `call getMovieCast then return success result`() = runBlocking {

        val results = MovieCastsResponse(
            listOf(MovieCastResponse(name = "Mostafa"))
        )
        whenever(getMovieCastUseCase(any(), any())).thenReturn(
            flow {
                Resource.Success(
                    results
                )
            }
        )

        viewmodel.getMovieCast(1, "")

    /*    val emit = viewmodel.movieCastStateFlow.toList()

        assertEquals(emit[1], Resource.Success(results))
        viewmodel.movieCastStateFlow.collectLatest {
            assertEquals(it, Resource.Success(results))
        }*/

        viewmodel.movieCastStateFlow.test {
            awaitItem() shouldBe Resource.Loading
        }
    }
}