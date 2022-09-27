package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.MovieItemModel
import com.example.movieapp.domain.model.toMovieItemModel
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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