package com.example.tmbdmovies.di

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.presentation.mappers.MovieCastDomainMapper
import com.example.tmbdmovies.presentation.mappers.MovieGenreDomainMapper
import com.example.tmbdmovies.presentation.mappers.MovieRemoteMapper
import com.example.tmbdmovies.presentation.mappers.MovieTrailerDomainMapper
import com.example.tmbdmovies.presentation.model.MovieCastPresentation
import com.example.tmbdmovies.presentation.model.MovieGenrePresentation
import com.example.tmbdmovies.presentation.model.MoviePresentation
import com.example.tmbdmovies.presentation.model.MovieTrailerPresentation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    
    @Binds
    abstract fun bindMovieCastDomainMapper(movieCastDomainMapper: MovieCastDomainMapper): Mapper<MovieCastResponse, MovieCastPresentation>

    @Binds
    abstract fun bindMovieTrailersDomainMapper(movieTrailerDomainMapper: MovieTrailerDomainMapper): Mapper<MovieTrailerResponse, MovieTrailerPresentation>

    @Binds
    abstract fun bindMovieGenresDomainMapper(movieGenreDomainMapper: MovieGenreDomainMapper): Mapper<MovieGenreResponse, MovieGenrePresentation>

    @Binds
    abstract fun bindMoviesMapper(movieRemoteMapper: MovieRemoteMapper): Mapper<MovieResponse, MoviePresentation>

}