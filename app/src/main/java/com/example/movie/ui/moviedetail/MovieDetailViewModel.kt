package com.example.movie.ui.moviedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.movie.data.entities.Detail
import com.example.movie.repository.DetailRepository
import com.example.movie.utils.Resource

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _detail = _id.switchMap { id ->
        repository.getMovie(id)
    }
    val detail: LiveData<Resource<Detail>> = _detail


    fun start(id: String) {
        _id.value = id
    }
}