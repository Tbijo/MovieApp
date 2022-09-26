package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.use_case.GetMoviesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovies: GetMoviesApi
): ViewModel() {

    fun getMovie() {
        getMovies().onEach { result->
            println(result[0])
        }.launchIn(viewModelScope)
    }
}