package com.example.moviecodingtest.details.presentation

import com.example.moviecodingtest.movieList.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)