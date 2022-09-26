package com.example.movieapp.domain.use_case

import com.example.movieapp.data.remote.dto.MovieApiDto
import com.example.movieapp.domain.repository.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesApi @Inject constructor(
    private val movieApiRepository: MovieApiRepository
) {
    operator fun invoke(): Flow<List<MovieApiDto>> = flow {
        try {
            emit(movieApiRepository.getMovies())
        } catch (e: HttpException) {
            // response code does not start with 2
            e.printStackTrace()
            emit(emptyList())
        } catch (e: IOException) {
            // API has no connection to Remote Data
            e.printStackTrace()
            emit(emptyList())
        }
    }
}