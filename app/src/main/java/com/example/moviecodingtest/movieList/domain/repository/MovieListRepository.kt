package com.example.moviecodingtest.movieList.domain.repository

import com.example.moviecodingtest.movieList.domain.model.Movie
import com.example.moviecodingtest.movieList.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}