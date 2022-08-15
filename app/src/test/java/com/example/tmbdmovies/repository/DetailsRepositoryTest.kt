package com.example.tmbdmovies.repository

import app.cash.turbine.test
import com.example.tmbdmovies.MainCoroutineRule
import com.example.tmbdmovies.common.NetworkConnectivityHelper
import com.example.tmbdmovies.common.Resource
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieCastsResponse
import com.example.tmbdmovies.data.remote.MoviesRemoteDateSource
import com.example.tmbdmovies.data.repository.DetailsRepositoryImp
import com.example.tmbdmovies.domain.repository.DetailsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
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
class DetailsRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDateSource

    @Mock
    private lateinit var networkConnectivityHelper: NetworkConnectivityHelper

    private lateinit var detailsRepository: DetailsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        detailsRepository =
            DetailsRepositoryImp(remoteDataSource, networkConnectivityHelper, Dispatchers.Main)
    }

    @Test
    fun `call success getMovieCast then return success result`() = runTest {

        val results = MovieCastsResponse(
            listOf(MovieCastResponse(name = "Mostafa"))
        )

        Mockito.`when`(remoteDataSource.getMovieCast(any())).thenReturn(
            results
        )
        whenever(networkConnectivityHelper.isConnected()).thenReturn(true)

        detailsRepository.getMovieCast(1).test {
            awaitItem() shouldBeInstanceOf Resource.Loading::class.java
            awaitItem() shouldBeEqualTo  Resource.Success(results)
            awaitComplete()
        }
    }

    @Test
    fun `call failed getMovieCast then return failure result`() = runTest {

        Mockito.`when`(remoteDataSource.getMovieCast(any())).thenThrow(
            Exception()
        )
        whenever(networkConnectivityHelper.isConnected()).thenReturn(true)

        detailsRepository.getMovieCast(1).test {
            awaitItem() shouldBeInstanceOf Resource.Loading::class.java
            awaitItem() shouldBeInstanceOf Resource.Error::class.java
            awaitComplete()
        }
    }
}