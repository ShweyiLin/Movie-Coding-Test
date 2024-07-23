package com.example.moviecodingtest.details.presentation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun DetailsScreen() {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState by detailsViewModel.detailsState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp)),
            factory = { context->
                YouTubePlayerView(context = context).apply {
                    lifecycleOwner.lifecycle.addObserver(this)

                    addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(detailsState.detailMovieKey, 0f)
                        }
                    })
                }
            }
        )


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