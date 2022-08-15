package com.example.tmbdmovies.viewmodel

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.tmbdmovies.MainCoroutineRule
import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.domain.usecases.GetMoviesUseCase
import com.example.tmbdmovies.presentation.mappers.MovieRemoteMapper
import com.example.tmbdmovies.presentation.model.MoviePresentation
import com.example.tmbdmovies.presentation.movies.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MoviesViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    lateinit var viewmodel: MoviesViewModel

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val mapper: Mapper<MovieResponse, MoviePresentation> = MovieRemoteMapper()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewmodel = MoviesViewModel(getMoviesUseCase, mapper)
    }

    @Test
    fun `call getMovies then getMoviesUseCase should be called`() = runTest {
        val QUERY_TYPE = "popular"

        val movies = listOf(
            MovieResponse(original_name = "Movie name")
        )

        Mockito.`when`(getMoviesUseCase(QUERY_TYPE)).thenReturn(
            flow {
                PagingData.from(movies)
            }
        )

        viewmodel.getMovies(QUERY_TYPE)

        viewmodel.requestStateFlow.test {
            awaitItem() shouldBe QUERY_TYPE
        }

    }
}