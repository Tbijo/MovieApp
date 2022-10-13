package com.example.movieapp.feat_movie.presentation.movie_list

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.movieapp.R
import com.example.movieapp.common.Resource
import com.example.movieapp.feat_movie.domain.model.MovieItemModel
import com.example.movieapp.feat_movie.domain.service.DownloadVideoWorker
import com.example.movieapp.feat_movie.domain.use_case.*
import com.example.movieapp.feat_movie.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val appContext: Application
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
    private var downloadDataJob: Job? = null
    private var restoreDataJob: Job? = null

    init {
        // if no data Download, Store, Show else Show
        movieUseCases.getMovies().onEach { movieItemList: List<MovieItemModel> ->
            if (movieItemList.isEmpty()) {
                downloadData()
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

            // to Download, Save and show data
            MovieListEvent.RestoreData -> {
                // only restore data if downloading failed
                if (movieListState.value.movies.isEmpty()) {
                    downloadData()
                } else {
                    restoreData()
                }
            }

            // to show and hide Order Section
            MovieListEvent.ToggleOrderSection -> {
                _movieListState.value = movieListState.value.copy(
                    isOrderSectionVisible = !movieListState.value.isOrderSectionVisible
                )
            }
            // to download and store a video, save file path in corresponding video row in db
            is MovieListEvent.DownloadVideo -> {
                startWorkManager(event.url, event.movieName, event.movieId)
            }
        }
    }

    private fun downloadData() {
        downloadDataJob?.cancel()
        downloadDataJob = movieUseCases.getMoviesApi().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movieUseCases.addMovies(result.data ?: emptyList())
                    movieUseCases.getMovies().onEach {
                        _movieListState.value = MovieListState(movies = it)
                    }
                }
                is Resource.Error -> {
                    movieUseCases.getMovies().onEach {
                        _movieListState.value = MovieListState(
                            movies = it,
                            error = result.message ?: "An unexpected error has occurred"
                        )
                    }
                }
                is Resource.Loading -> {
                    _movieListState.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun restoreData() {
        restoreDataJob?.cancel()
        restoreDataJob = movieUseCases.getMovies().onEach { movieItemList: List<MovieItemModel> ->
            _movieListState.value = MovieListState(movies = movieItemList)
        }.launchIn(viewModelScope)
    }

    private fun findSortMovies(name: String, orderType: OrderType) {
        findMovieJob?.cancel()
        findMovieJob = movieUseCases.getMoviesByName(name, orderType).onEach {
            _movieListState.value = movieListState.value.copy(
                movies = it,
                orderType = orderType
            )
        }.launchIn(viewModelScope)
    }

    private fun startWorkManager(url: String, movieName: String, movieId: Long) {
        val downloadRequest = OneTimeWorkRequestBuilder<DownloadVideoWorker>()
            .setInputData(
                Data.Builder()
                    .putString(appContext.resources.getString(R.string.url), url)
                    .putString(appContext.resources.getString(R.string.movie_name), movieName)
                    .putLong(appContext.resources.getString(R.string.movie_id), movieId)
                    .build()
            )
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .build()

        WorkManager.getInstance(appContext)
            .beginUniqueWork(
                "download",
                ExistingWorkPolicy.APPEND_OR_REPLACE,
                downloadRequest
            )
            .enqueue()
    }

}