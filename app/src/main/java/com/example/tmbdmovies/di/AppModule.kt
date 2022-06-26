package com.example.tmbdmovies.di

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.mappers.MovieRemoteMapper
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.presentation.mappers.MovieDomainMapper
import com.example.tmbdmovies.presentation.model.MoviePresentation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    abstract fun bindMovieRemoteMapper(movieRemoteMapper: MovieRemoteMapper): Mapper<MovieResponse, Movie>

    @Binds
    abstract fun bindMovieDomainMapper(movieDomainMapper: MovieDomainMapper): Mapper<Movie, MoviePresentation>

}