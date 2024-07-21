package com.example.moviecodingtest.movieList.presentation

import com.example.moviecodingtest.movieList.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,

    val popularMovieList: List<Movie> = emptyList(),
)