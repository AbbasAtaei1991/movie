package com.example.movie.data.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbID")
    @PrimaryKey
    val imdbID: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String
)