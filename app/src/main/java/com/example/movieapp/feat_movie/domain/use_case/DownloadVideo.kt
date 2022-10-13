package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.domain.repository.VideoApiRepository
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class DownloadVideo(
    private val videoApiRepository: VideoApiRepository
) {

    suspend operator fun invoke(fileUrl: String): Response<ResponseBody>? {
        return try {
            videoApiRepository.downloadVideo(fileUrl)
        } catch (e: IOException) {
            null
        } catch (e: HttpException) {
            null
        }
    }
}