package com.example.movieapp.feat_movie.domain.service

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.movieapp.R
import com.example.movieapp.feat_movie.data.remote_video.VideoApi
import com.example.movieapp.feat_movie.domain.use_case.MovieUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

@HiltWorker
class DownloadVideoWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val videoApi: VideoApi,
    private val movieUseCases: MovieUseCases
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        startForegroundService("Download Started", "Downloading...")

        val url: String = inputData.getString("url")!!
        val movieName: String = inputData.getString("movieName")!!
        val movieId: Long = inputData.getLong("movieId", 0L)

        val response = videoApi.downloadVideo(url)
        response.body()?.let { body ->
            return withContext(Dispatchers.IO) {
                val file = File(context.cacheDir, "$movieName.mp4")
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    try {
                        stream.write(body.bytes())
                    } catch(e: IOException) {
                        return@withContext Result.failure(
                            workDataOf(
                                context.resources.getString(R.string.error_message)  to e.localizedMessage
                            )
                        )
                    }
                }
                movieUseCases.updateMovie(movieId, "${file.toUri()}")
                startForegroundService("Download Finished", "Video is stored to ${file.toUri()}")
                Result.success()
            }
        }
        if(!response.isSuccessful) {
            if(response.code().toString().startsWith("5")) {
                return Result.retry()
            }
            return Result.failure(
                workDataOf(
                    context.resources.getString(R.string.error_message) to "Network error"
                )
            )
        }
        return Result.failure(
            workDataOf(context.resources.getString(R.string.error_message) to "Unknown error")
        )
    }

    private suspend fun startForegroundService(title: String, text: String) {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, "download_channel") // our channel
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText(text)
                    .setContentTitle(title)
                    .build()
            )
        )
    }
}