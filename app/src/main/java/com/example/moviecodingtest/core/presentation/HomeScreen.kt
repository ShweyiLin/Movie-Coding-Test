package com.example.moviecodingtest.core.presentation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviecodingtest.movieList.presentation.MovieListViewModel
import com.example.moviecodingtest.movieList.presentation.NowPlayingMovieScreen
import com.example.moviecodingtest.movieList.presentation.PopularMoviesScreen
import com.example.moviecodingtest.movieList.presentation.UpComingMovieScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieListState = movieListViewModel.movieListState.collectAsState().value

    var text by rememberSaveable { mutableStateOf("") }
//    val state = rememberScrollState()
//    LaunchedEffect(Unit) { state.animateScrollTo(100) }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Movie Lists",
                    fontSize = 20.sp
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon", tint = Color.Black)
                }
            },
            modifier = Modifier.shadow(2.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                MaterialTheme.colorScheme.inverseOnSurface
            )
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {

                Text(text = "Upcoming Movies", fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(top = 25.dp).padding(horizontal = 16.dp))
                UpComingMovieScreen(
                    navController = navController,
                    movieListState = movieListState,
                    onEvent = movieListViewModel::onEvent
                )

                Text(text = "Popular Movies", fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(top = 25.dp).padding(horizontal = 16.dp))
                PopularMoviesScreen(
                    navController = navController,
                    movieListState = movieListState,
                    onEvent = movieListViewModel::onEvent
                )
                Text(text = "Now Playing Movies", fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(top = 25.dp).padding(horizontal = 16.dp))
                NowPlayingMovieScreen(
                    navController = navController,
                    movieListState = movieListState,
                    onEvent = movieListViewModel::onEvent
                )
            }

        }
    }

}

