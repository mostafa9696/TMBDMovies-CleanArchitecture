package com.example.tmbdmovies.di

import com.example.tmbdmovies.data.remote.MoviesRemoteDateSource
import com.example.tmbdmovies.data.remote.MoviesRemoteDateSourceImp
import com.example.tmbdmovies.data.repository.DetailsRepositoryImp
import com.example.tmbdmovies.data.repository.MoviesRepositoryImp
import com.example.tmbdmovies.domain.repository.DetailsRepository
import com.example.tmbdmovies.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(moviesRepositoryImp: MoviesRepositoryImp): MoviesRepository

    @Binds
    abstract fun bindDetailsRepository(detailsRepository: DetailsRepositoryImp): DetailsRepository

    @Binds
    abstract fun bindMovieRemoteDataSource(moviesRemoteDateSourceImp: MoviesRemoteDateSourceImp): MoviesRemoteDateSource
}