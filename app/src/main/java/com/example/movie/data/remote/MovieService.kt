package com.example.movie.data.remote

import com.example.movie.data.entities.Detail
import com.example.movie.data.entities.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("s") s: String
    ): Response<MovieList>

    @GET("")
    suspend fun getDetail(
        @Query("api_key") apiKey: String,
        @Query("i") i: String
    ): Response<Detail>
}