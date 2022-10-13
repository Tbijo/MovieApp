package com.example.movieapp.feat_movie.data.remote_video.repository

import com.example.movieapp.feat_movie.data.remote_video.VideoApi
import com.example.movieapp.feat_movie.domain.repository.VideoApiRepository
import okhttp3.ResponseBody
import retrofit2.Response

class VideoApiRepositoryImpl(
    private val videoApi: VideoApi
): VideoApiRepository {

    override suspend fun downloadVideo(fileURL: String): Response<ResponseBody> {
        return videoApi.downloadVideo(fileURL)
    }
}