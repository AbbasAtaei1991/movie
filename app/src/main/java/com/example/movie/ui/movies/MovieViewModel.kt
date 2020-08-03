package com.example.movie.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.movie.repository.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    val movies = repository.getMovies()
}