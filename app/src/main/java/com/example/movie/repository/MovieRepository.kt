package com.example.movie.repository

import com.example.movie.data.local.MovieDao
import com.example.movie.data.remote.MovieRemoteDataSource
import com.example.movie.utils.performGetOperation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) {

//    fun getMovie(id: String) = performGetOperation(
//        databaseQuery = { localDataSource.getMovie(id) },
//        networkCall = { remoteDataSource.getMovie(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies() },
        saveCallResult = { localDataSource.insertAll(it.search) }
    )
}