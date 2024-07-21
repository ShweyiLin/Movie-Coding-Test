package com.example.moviecodingtest.details.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.moviecodingtest.R
import com.example.moviecodingtest.movieList.data.remote.MovieApi

@Composable
fun DetailsScreen() {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.detailsState.collectAsState().value

    val backDropImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val posterImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (backDropImageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = detailsState.movie?.title
                )
            }
        }

        if (backDropImageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
                    )
                    .height(270.dp),
                painter = backDropImageState.painter,
                contentDescription = detailsState.movie?.title,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        detailsState.movie?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = it.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 4.dp)
        ) {
            Text(text = "Votes: ", fontWeight = FontWeight.SemiBold)
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = detailsState.movie?.vote_average.toString().take(3),
                fontSize = 14.sp,
                maxLines = 1,
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 2.dp)
        ) {
            Text(text = "Language: ", fontWeight = FontWeight.SemiBold)
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = detailsState.movie?.original_language.toString(),
                fontSize = 14.sp,
                maxLines = 1,
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 2.dp, bottom = 12.dp)
        ) {
            Text(text = "Release Date: ", fontWeight = FontWeight.SemiBold)
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = detailsState.movie?.release_date.toString(),
                fontSize = 14.sp,
                maxLines = 1,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        detailsState.movie?.let {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = it.overview,
                    fontSize = 16.sp,
                )
            }

        Spacer(modifier = Modifier.height(32.dp))


    }

}