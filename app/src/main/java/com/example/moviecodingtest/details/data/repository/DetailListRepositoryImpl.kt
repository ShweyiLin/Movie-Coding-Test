package com.example.moviecodingtest.details.data.repository

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecodingtest.details.data.remote.DetailApi
import com.example.moviecodingtest.details.data.remote.response.DetailDto
import com.example.moviecodingtest.details.data.remote.response.DetailListDto
import com.example.moviecodingtest.details.domain.repository.DetailListRepository
import com.example.moviecodingtest.details.presentation.DetailsState
import com.example.moviecodingtest.details.presentation.DetailsViewModel
import com.example.moviecodingtest.movieList.data.local.movie.MovieDatabase
import com.example.moviecodingtest.movieList.data.mappers.toMovie
import com.example.moviecodingtest.movieList.data.mappers.toMovieEntity
import com.example.moviecodingtest.movieList.data.remote.MovieApi
import com.example.moviecodingtest.movieList.domain.model.Movie
import com.example.moviecodingtest.movieList.domain.repository.MovieListRepository
import com.example.moviecodingtest.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DetailListRepositoryImpl @Inject constructor(
    private val detailApi: DetailApi,
) : DetailListRepository {

    private var _detialsState = MutableStateFlow(DetailsState())

    override suspend fun getMovieDetail(
        id: Int
    ): Flow<Resource<DetailListDto>> {
        return flow {

            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(detailApi.getMovieDetail(id)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
            }
            emit(Resource.Loading(false))

        }
    }
}