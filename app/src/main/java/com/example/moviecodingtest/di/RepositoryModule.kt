package com.example.moviecodingtest.di

import com.example.moviecodingtest.details.data.repository.DetailListRepositoryImpl
import com.example.moviecodingtest.details.domain.repository.DetailListRepository
import com.example.moviecodingtest.movieList.data.repository.MovieListRepositoryImpl
import com.example.moviecodingtest.movieList.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ): MovieListRepository

    @Binds
    @Singleton
    abstract fun bindDetailListRepository(
        detailListRepositoryImpl: DetailListRepositoryImpl
    ): DetailListRepository
}