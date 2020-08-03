package com.example.movie.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.entities.Detail

@Dao
interface DetailDao {

    @Query("SELECT * FROM details")
    fun getAllDetails() : LiveData<List<Detail>>

    @Query("SELECT * FROM details WHERE imdbID = :id")
    fun getDetail(id: String): LiveData<Detail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(details: List<Detail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: Detail)
}