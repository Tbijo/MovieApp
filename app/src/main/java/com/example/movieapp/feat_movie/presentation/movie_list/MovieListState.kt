package com.example.movieapp.feat_movie.presentation.movie_list

import com.example.movieapp.feat_movie.domain.model.MovieItemModel
import com.example.movieapp.feat_movie.domain.util.OrderType

data class MovieListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<MovieItemModel> = emptyList(),
    val orderType: OrderType = OrderType.Descending,
    val isOrderSectionVisible: Boolean = false
)