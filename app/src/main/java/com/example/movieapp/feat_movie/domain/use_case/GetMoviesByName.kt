package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.domain.model.MovieItemModel
import com.example.movieapp.feat_movie.domain.model.toMovieItemModel
import com.example.movieapp.feat_movie.domain.repository.MovieRepository
import com.example.movieapp.feat_movie.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesByName(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(
        name: String = "",
        orderType: OrderType = OrderType.Descending
    ): Flow<List<MovieItemModel>> {
        return movieRepository.getMoviesByName("%$name%").map { list->
            when(orderType) {
                is OrderType.Ascending -> {
                    list.map { it.toMovieItemModel() }
                        .sortedBy { it.name }
                }
                is OrderType.Descending -> {
                    list.map { it.toMovieItemModel() }
                        .sortedByDescending { it.name }
                }
            }
        }
    }

}