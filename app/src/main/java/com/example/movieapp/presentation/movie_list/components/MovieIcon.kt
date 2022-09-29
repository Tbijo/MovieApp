package com.example.movieapp.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter

import com.example.movieapp.R

@ExperimentalCoilApi
@Composable
fun MovieIcon(imageUrl: String) {
    Box {
        val painter = rememberImagePainter(data = imageUrl, builder = {
            placeholder(R.drawable.ic_broken_image)
            error(R.drawable.ic_broken_image)
        })
        val painterState = painter.state
        Image(painter = painter, contentDescription = "Movie Icon")
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}