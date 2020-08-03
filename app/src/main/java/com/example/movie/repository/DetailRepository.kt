package com.example.movie.repository

import com.example.movie.data.local.DetailDao
import com.example.movie.data.local.MovieDao
import com.example.movie.data.remote.MovieRemoteDataSource
import com.example.movie.utils.performGetOperation
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: DetailDao
) {

    fun getMovie(id: String) = performGetOperation(
        databaseQuery = { localDataSource.getDetail(id) },
        networkCall = { remoteDataSource.getMovie(id) },
        saveCallResult = { localDataSource.insert(it) }
    )
}