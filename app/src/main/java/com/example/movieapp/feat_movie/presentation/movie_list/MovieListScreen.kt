package com.example.movieapp.feat_movie.presentation.movie_list

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.movieapp.feat_movie.presentation.movie_list.components.MovieItem
import com.example.movieapp.feat_movie.presentation.util.Screens
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.R
import com.example.movieapp.feat_movie.domain.model.MovieItemModel
import com.example.movieapp.feat_movie.presentation.movie_list.components.OrderSection

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.movieListState.value
    val textState = viewModel.nameText.value
    val mContext = LocalContext.current

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        viewModel.onEvent(MovieListEvent.RestoreData)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Refresh",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = "Videos",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(MovieListEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = mContext.resources.getString(R.string.search),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .testTag(mContext.getString(R.string.order_section)),
                    onOrderChange = {
                        viewModel.onEvent(MovieListEvent.Order(it, textState.text))
                    },
                    orderType = state.orderType,
                    name = textState.text,
                    hint = textState.hint,
                    onTextChange = {
                        viewModel.onEvent(MovieListEvent.EnteredText(state.orderType, it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(MovieListEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = textState.isHintVisible
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                content = {
                    items(state.movies) { movie->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(5.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            MovieItem(
                                movie = movie,
                                onItemClicked = {
                                    if (isMovieAvailable(movie)) {
                                        navController.navigate(Screens.MoviePlayerScreen.route + "?movieUri=${movie.manifestUri}")
                                    } else {
                                        Toast.makeText(mContext, "Movie unavailable.", Toast.LENGTH_LONG).show()
                                    }
                                },
                                onBtnClick = {
                                    if (movie.filePath != null) {
                                        Toast.makeText(mContext, "File unplayable.", Toast.LENGTH_LONG).show()
                                        //navController.navigate(Screens.MoviePlayerScreen.route + "?movieUri=${movie.filePath}")
                                    } else {
                                        if (isMovieAvailable(movie)) {
                                            viewModel.onEvent(MovieListEvent.DownloadVideo(
                                                url = movie.manifestUri!!,
                                                movieName = movie.name!!,
                                                movieId = movie.id
                                            ))
                                        } else {
                                            Toast.makeText(mContext, "Movie unavailable.", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                },
                                btnText = if (movie.filePath != null) "Play Offline" else "Download"
                            )
                        }
                    }
                }
            )
            if (state.error.isNotBlank() && state.movies.isEmpty()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(CenterHorizontally)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }
    }
}

private fun isMovieAvailable(movie: MovieItemModel): Boolean {
    if (movie.drm?.contains("DEMO_CLEAR") == false) {
        return false
    }
    if (movie.manifestUri == null) {
        return false
    }
    return true
}