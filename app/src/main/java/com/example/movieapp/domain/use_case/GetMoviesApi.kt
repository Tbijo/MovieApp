package com.example.movieapp.domain.use_case

import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.dto.MovieApiDto
import com.example.movieapp.domain.repository.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesApi(
    private val movieApiRepository: MovieApiRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieApiDto>>> = flow {
        try {
            emit(Resource.Loading<List<MovieApiDto>>())
            val movies = movieApiRepository.getMovies()
            emit(Resource.Success<List<MovieApiDto>>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<MovieApiDto>>(e.localizedMessage ?: "Server Error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<MovieApiDto>>("Server unreachable."))
        }
    }
}