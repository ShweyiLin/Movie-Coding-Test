package com.example.moviecodingtest.details.domain.repository

import com.example.moviecodingtest.details.data.remote.response.DetailDto
import com.example.moviecodingtest.details.data.remote.response.DetailListDto
import com.example.moviecodingtest.movieList.domain.model.Movie
import com.example.moviecodingtest.movieList.util.Resource
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface DetailListRepository {
    suspend fun getMovieDetail(
        id: Int
    ): Flow<Resource<DetailListDto>>
}