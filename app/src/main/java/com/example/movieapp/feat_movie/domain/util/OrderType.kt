package com.example.movieapp.feat_movie.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
