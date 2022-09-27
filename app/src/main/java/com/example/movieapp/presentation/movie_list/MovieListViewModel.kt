package com.example.movieapp.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.MovieItemModel
import com.example.movieapp.domain.use_case.*
import com.example.movieapp.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    // State for text field to filter movies by name
    private val _nameText = mutableStateOf(
        TextFieldState(
            hint = "Enter movie..."
        )
    )
    val nameText: State<TextFieldState> = _nameText

    // State for the rest of MovieListScreen (refresh data, get data, order and filter data)
    private val _movieListState = mutableStateOf<MovieListState>(MovieListState())
    val movieListState: State<MovieListState> = _movieListState

    // to not have multiple instances of this flow
    // the old coroutine will be canceled
    private var findMovieJob: Job? = null
    private var restoreData: Job? = null

    init {
        // if no data Download, Store, Show else Show
        movieUseCases.getMovies().onEach { movieItemList: List<MovieItemModel> ->
            if (movieItemList.isEmpty()) {
                restoreData()
            } else {
                _movieListState.value = MovieListState(movies = movieItemList)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            // to hide hint text
            is MovieListEvent.ChangeTitleFocus -> {
                _nameText.value = nameText.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            nameText.value.text.isBlank()
                )
            }

            // to view text in text field
            // and search for movies with text and order type
            is MovieListEvent.EnteredText -> {
                _nameText.value = nameText.value.copy(
                    text = event.name
                )
                val name: String = event.name
                val orderType: OrderType = event.orderType
                findSortMovies(name, orderType)
            }

            // to reorder movies with the given text and order type
            is MovieListEvent.Order -> {
                if (movieListState.value.orderType == event.orderType) {
                    // if order type was not changed no action
                    return
                }
                val name: String = event.name
                val orderType: OrderType = event.orderType
                findSortMovies(name, orderType)
            }

            // to Download, Store and show data
            MovieListEvent.RestoreData -> restoreData()

            // to show and hide Order Section
            MovieListEvent.ToggleOrderSection -> {
                _movieListState.value = movieListState.value.copy(
                    isOrderSectionVisible = !movieListState.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun restoreData() {
        restoreData?.cancel()
        restoreData = movieUseCases.getMoviesApi().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movieUseCases.addMovies(result.data ?: emptyList())
                    movieUseCases.getMovies().onEach {
                        _movieListState.value = MovieListState(movies = it)
                    }
                }
                is Resource.Error -> {
                    _movieListState.value = MovieListState(
                        error = result.message ?: "An unexpected error has occurred"
                    )
                }
                is Resource.Loading -> {
                    _movieListState.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun findSortMovies(name: String, orderType: OrderType) {
        findMovieJob?.cancel()
        findMovieJob = movieUseCases.getMoviesByName(name, orderType).onEach {
            _movieListState.value = MovieListState(movies = it, orderType = orderType)
        }.launchIn(viewModelScope)
    }

}