package com.example.movieapp.feat_movie.domain.service

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.movieapp.R
import com.example.movieapp.feat_movie.data.remote_video.VideoApi
import com.example.movieapp.feat_movie.domain.use_case.DownloadVideo
import com.example.movieapp.feat_movie.domain.use_case.MovieUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

@HiltWorker
class DownloadVideoWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val downloadVideo: DownloadVideo,
    private val movieUseCases: MovieUseCases
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val url: String = inputData.getString(context.resources.getString(R.string.url))!!
        val movieName: String = inputData.getString(context.resources.getString(R.string.movie_name))!!
        val movieId: Long = inputData.getLong(context.resources.getString(R.string.movie_id), 0L)

        val response = downloadVideo(url)

        response?.body()?.let { body ->
            return withContext(Dispatchers.IO) {
                val file = File(context.cacheDir, "$movieName.mp4")
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    try {
                        stream.write(body.bytes())
                    } catch(e: IOException) {

                        startForegroundService("Download Failure", "Unable to store the video.", context.resources.getString(R.string.chan_done))
                        return@withContext Result.failure()
                    }
                }
                movieUseCases.addFilePathToMovie(movieId, "${file.toUri()}")
                startForegroundService("Download Finished", "Video is stored to ${file.toUri()}", context.resources.getString(R.string.chan_done))
                Result.success()
            }
        }

        if(response?.isSuccessful == false) {
            if(response.code().toString().startsWith("5")) {
                return Result.retry()
            }

            startForegroundService("Download Failure", "Network Error", context.resources.getString(R.string.chan_done))
            return Result.failure()
        }

        startForegroundService("Download Failure", "Unknown Error", context.resources.getString(R.string.chan_done))
        return Result.failure()
    }

    private suspend fun startForegroundService(title: String, text: String, channelName: String) {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, channelName) // our channel
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText(text)
                    .setContentTitle(title)
                    .build()
            )
        )
    }
}