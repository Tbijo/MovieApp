package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.local.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
@TypeConverters(MyConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}