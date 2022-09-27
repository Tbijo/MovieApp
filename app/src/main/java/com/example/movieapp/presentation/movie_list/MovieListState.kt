package com.example.movieapp.presentation.movie_list

import com.example.movieapp.domain.model.MovieItemModel
import com.example.movieapp.domain.util.OrderType

data class MovieListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<MovieItemModel> = emptyList(),
    val orderType: OrderType = OrderType.Descending,
    val isOrderSectionVisible: Boolean = false
)