package com.example.tmbdmovies.di

import com.example.tmbdmovies.common.Mapper
import com.example.tmbdmovies.data.mappers.MovieCastsRemoteMapper
import com.example.tmbdmovies.data.mappers.MovieGenresRemoteMapper
import com.example.tmbdmovies.data.mappers.MovieRemoteMapper
import com.example.tmbdmovies.data.mappers.MovieTrailerRemoteMapper
import com.example.tmbdmovies.data.models.MovieCastResponse
import com.example.tmbdmovies.data.models.MovieGenreResponse
import com.example.tmbdmovies.data.models.MovieResponse
import com.example.tmbdmovies.data.models.MovieTrailerResponse
import com.example.tmbdmovies.domain.models.Movie
import com.example.tmbdmovies.domain.models.MovieCast
import com.example.tmbdmovies.domain.models.MovieGenre
import com.example.tmbdmovies.domain.models.MovieTrailer
import com.example.tmbdmovies.presentation.mappers.MovieCastDomainMapper
import com.example.tmbdmovies.presentation.mappers.MovieDomainMapper
import com.example.tmbdmovies.presentation.mappers.MovieGenreDomainMapper
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
    abstract fun bindMovieRemoteMapper(movieRemoteMapper: MovieRemoteMapper): Mapper<MovieResponse, Movie>

    @Binds
    abstract fun bindMovieDomainMapper(movieDomainMapper: MovieDomainMapper): Mapper<Movie, MoviePresentation>

    @Binds
    abstract fun bindMovieCastRemoteMapper(movieCastsRemoteMapper: MovieCastsRemoteMapper): Mapper<MovieCastResponse, MovieCast>

    @Binds
    abstract fun bindMovieCastDomainMapper(movieCastDomainMapper: MovieCastDomainMapper): Mapper<MovieCast, MovieCastPresentation>

    @Binds
    abstract fun bindMovieTrailersRemoteMapper(movieTrailerRemoteMapper: MovieTrailerRemoteMapper): Mapper<MovieTrailerResponse, MovieTrailer>

    @Binds
    abstract fun bindMovieTrailersDomainMapper(movieTrailerDomainMapper: MovieTrailerDomainMapper): Mapper<MovieTrailer, MovieTrailerPresentation>

    @Binds
    abstract fun bindMovieGenresRemoteMapper(movieGenresRemoteMapper: MovieGenresRemoteMapper): Mapper<MovieGenreResponse, MovieGenre>

    @Binds
    abstract fun bindMovieGenresDomainMapper(movieGenreDomainMapper: MovieGenreDomainMapper): Mapper<MovieGenre, MovieGenrePresentation>
}