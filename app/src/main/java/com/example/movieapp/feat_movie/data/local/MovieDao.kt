package com.example.movieapp.feat_movie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.feat_movie.data.local.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("UPDATE movie SET filePath = :filePath WHERE id = :movieId")
    suspend fun updateMovie(movieId: Long, filePath: String)

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE name LIKE :name")
    fun getMoviesByName(name: String): Flow<List<Movie>>
}