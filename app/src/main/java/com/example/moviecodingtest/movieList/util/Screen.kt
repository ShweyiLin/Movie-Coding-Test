package com.example.moviecodingtest.movieList.util


sealed class Screen(val route: String) {
    data object Home: Screen(route = "main")
    data object Details: Screen(route = "details")
}