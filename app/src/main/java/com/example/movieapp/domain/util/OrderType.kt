package com.example.movieapp.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
