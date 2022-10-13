package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.feat_movie.data.local.MovieDatabase
import com.example.movieapp.feat_movie.data.local.repository.MovieRepositoryImpl
import com.example.movieapp.feat_movie.data.remote.repository.TestApiRepository
import com.example.movieapp.feat_movie.domain.repository.MovieApiRepository
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import com.example.movieapp.feat_movie.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideTestApiRepository(): MovieApiRepository {
        return TestApiRepository()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            MovieDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

    @Provides
    @Singleton
    fun provideMovieUseCases(repo: MovieRepository, apiRepo: MovieApiRepository): MovieUseCases {
        return MovieUseCases(
            addMovies = AddMovies(repo),
            getMovies = GetMovies(repo),
            getMoviesByName = GetMoviesByName(repo),
            getMoviesApi = GetMoviesApi(apiRepo),
            addFilePathToMovie = AddFilePathToMovie(repo)
        )
    }
}