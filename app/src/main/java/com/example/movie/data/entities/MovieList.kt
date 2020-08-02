package com.example.movie.data.entities


import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Search")
    val search: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Response")
    val response: String
)