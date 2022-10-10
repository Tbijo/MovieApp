package com.example.movieapp.feat_movie.data.remote_video

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface VideoApi {

    @GET
    @Streaming
    suspend fun downloadVideo(@Url fileURL: String): Response<ResponseBody>
}
