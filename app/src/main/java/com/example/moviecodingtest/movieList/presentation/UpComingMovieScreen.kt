package com.example.moviecodingtest.movieList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviecodingtest.movieList.presentation.componentes.MovieItem
import com.example.moviecodingtest.movieList.util.Category

@Composable
fun UpComingMovieScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {

    if (movieListState.upComingMovieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.height(320.dp),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            userScrollEnabled= true
        ) {
            items(movieListState.upComingMovieList.size) { index ->
                MovieItem(
                    movie = movieListState.upComingMovieList[index],
                    navHostController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (index >= movieListState.upComingMovieList.size - 1 && !movieListState.isLoading) {
                    onEvent(MovieListUiEvent.Paginate(Category.UPCOMING))
                }

            }
        }
    }
}






