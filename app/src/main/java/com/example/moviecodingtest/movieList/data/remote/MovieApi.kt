package com.example.moviecodingtest.movieList.data.remote

import com.example.moviecodingtest.movieList.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto


    companion object {
        const val API_KEY = "9165b14cb98b2ae3e429f266a8abc428"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val  IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780/"
    }

}