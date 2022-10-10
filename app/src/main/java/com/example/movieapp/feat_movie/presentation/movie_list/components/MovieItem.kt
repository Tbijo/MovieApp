package com.example.movieapp.feat_movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.R
import com.example.movieapp.feat_movie.domain.model.MovieItemModel

@ExperimentalCoilApi
@Composable
fun MovieItem(
    movie: MovieItemModel,
    onItemClicked: (MovieItemModel) -> Unit
) {
    val mContext = LocalContext.current

    Card(
        modifier = Modifier
            .testTag(mContext.resources.getString(R.string.movie_item))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(movie) }
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            MovieIcon(
                imageUrl = movie.iconUri ?: ""
            )
            Text(
                text = movie.name ?: "No name",
                style = TextStyle(Color.Black),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}