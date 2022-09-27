package com.example.movieapp.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.dto.MovieApiDto
import com.example.movieapp.domain.model.MovieItemModel
import com.example.movieapp.domain.use_case.*
import com.example.movieapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
): ViewModel() {

    init {
        // if no data Download, Store, Show else Show
    }

    fun onEvent(event: MovieListEvent) {
        when(event) {
            is MovieListEvent.ChangeTitleFocus -> TODO()
            is MovieListEvent.EnteredText -> TODO()
            is MovieListEvent.Order -> TODO()
            MovieListEvent.RestoreNote -> TODO()
            MovieListEvent.ToggleOrderSection -> TODO()
        }
    }

    fun getMovie() {
//        movieUseCases.getMoviesApi().onEach { result: List<MovieApiDto>->
//            addMovies(result)
//        }.launchIn(viewModelScope)

        movieUseCases.getMovies().onEach { list: List<MovieItemModel> ->
            println(list[0])
        }.launchIn(viewModelScope)

        movieUseCases.getMoviesByName("Bunny", OrderType.Descending).onEach {
            println(it)
        }.launchIn(viewModelScope)
    }
}