package com.example.movie.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.entities.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE imdbID = :id")
    fun getMovie(id: String): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)
}