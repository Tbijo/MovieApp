package com.example.movieapp.feat_movie.domain.repository

import okhttp3.ResponseBody
import retrofit2.Response

interface VideoApiRepository {
    suspend fun downloadVideo(fileURL: String): Response<ResponseBody>
}