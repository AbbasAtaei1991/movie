package com.example.movie.di

import android.content.Context
import com.example.movie.data.local.AppDatabase
import com.example.movie.data.local.MovieDao
import com.example.movie.data.remote.MovieRemoteDataSource
import com.example.movie.data.remote.MovieService
import com.example.movie.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) = MovieRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MovieRemoteDataSource,
                          localDataSource: MovieDao) =
        MovieRepository(remoteDataSource, localDataSource)
}