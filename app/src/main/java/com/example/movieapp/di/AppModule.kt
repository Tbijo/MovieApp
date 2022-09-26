package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.common.Constants
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.local.repository.MovieRepositoryImpl
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.remote.repository.MovieApiRepositoryImpl
import com.example.movieapp.domain.repository.MovieApiRepository
import com.example.movieapp.domain.repository.MovieRepository
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
    fun provideNoteDatabase(app: Application): MovieDatabase{
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
}