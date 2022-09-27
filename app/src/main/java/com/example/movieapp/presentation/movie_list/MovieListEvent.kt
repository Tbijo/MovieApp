package com.example.movieapp.presentation.movie_list

import androidx.compose.ui.focus.FocusState
import com.example.movieapp.domain.util.OrderType

sealed class MovieListEvent {

    // click to reorder the list
    data class Order(val orderType: OrderType, val name: String): MovieListEvent()

    // click to redownload data
    object RestoreNote: MovieListEvent()

    // Hide or Show order section
    object ToggleOrderSection: MovieListEvent()

    // click on keyboard
    data class EnteredText(val orderType: OrderType, val name: String): MovieListEvent()

    // click on textfield to hide the hint
    data class ChangeTitleFocus(val focusState: FocusState): MovieListEvent()
}