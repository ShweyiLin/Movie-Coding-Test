package com.example.moviecodingtest.details.data.remote

import com.example.moviecodingtest.details.data.remote.response.DetailListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {

    @GET("movie/{id}/videos")
    suspend fun getMovieDetail(
        @Path("id") id : Int,
        @Query("api_key") apiKey: String = API_KEY
    ): DetailListDto

    companion object {
        const val API_KEY = "9165b14cb98b2ae3e429f266a8abc428"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}