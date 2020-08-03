package com.example.movie.data.remote

import com.example.movie.utils.API_KEY
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource() {

    suspend fun getMovies() = getResult { movieService.getMovies(API_KEY, "batman") }
    suspend fun getMovie(id: String) = getResult { movieService.getDetail(API_KEY, id) }
}