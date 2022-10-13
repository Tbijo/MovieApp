package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.common.Constants
import com.example.movieapp.feat_movie.data.local.MovieDatabase
import com.example.movieapp.feat_movie.data.local.repository.MovieRepositoryImpl
import com.example.movieapp.feat_movie.data.remote.MovieApi
import com.example.movieapp.feat_movie.data.remote.repository.MovieApiRepositoryImpl
import com.example.movieapp.feat_movie.data.remote_video.VideoApi
import com.example.movieapp.feat_movie.data.remote_video.repository.VideoApiRepositoryImpl
import com.example.movieapp.feat_movie.domain.repository.MovieApiRepository
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import com.example.movieapp.feat_movie.domain.repository.VideoApiRepository
import com.example.movieapp.feat_movie.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideoApi(): VideoApi {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(VideoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVideoApiRepository(videoApi: VideoApi): VideoApiRepository {
        return VideoApiRepositoryImpl(videoApi)
    }

    @Provides
    @Singleton
    fun provideDownloadVideoUseCase(repo: VideoApiRepository): DownloadVideo {
        return DownloadVideo(repo)
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieApiRepository(api: MovieApi): MovieApiRepository {
        return MovieApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movie_db"
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