package com.example.moviecodingtest.details.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecodingtest.details.domain.repository.DetailListRepository
import com.example.moviecodingtest.movieList.domain.repository.MovieListRepository
import com.example.moviecodingtest.movieList.presentation.MovieListUiEvent
import com.example.moviecodingtest.movieList.util.Category
import com.example.moviecodingtest.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val detailListRepository: DetailListRepository,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private var _detialsState = MutableStateFlow(DetailsState())
    val detailsState = _detialsState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
        getMovieDetail(movieId ?: -1)
    }


    private fun getMovieDetail(id : Int) {
        Log.i("hello","Enter detail")
        viewModelScope.launch {
            _detialsState.update {
                it.copy(isLoading = true)
            }

            detailListRepository.getMovieDetail(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.i("hello", "Error in detail")
                        _detialsState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _detialsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { movie ->
                            Log.i("movie data list", "enter movies")
                            _detialsState.update {
                                it.copy(
                                    detailMovieKey = movie.results.first().key
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            _detialsState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovie(id).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _detialsState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _detialsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { movie ->
                            _detialsState.update {
                                it.copy(movie = movie)
                            }
                        }
                    }
                }
            }
        }
    }
}