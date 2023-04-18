package com.example.weather.data.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.weather.domain.useCases.weather.UpdateWeatherDataUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

const val TAG = "Updating Weather"

@HiltWorker
class UpdateWeatherWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    val updateWeathersUseCase: UpdateWeatherDataUseCase,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                updateWeathersUseCase()
                Result.success()
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    throwable.message,
                    throwable
                )
                Result.failure()
            }
        }
    }
}

fun updateWeatherWorker(@ApplicationContext context: Context) {

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val periodicWorkRequest =
        PeriodicWorkRequestBuilder<UpdateWeatherWorker>(
            60,
            TimeUnit.MINUTES,
            30,
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        TAG,
        ExistingPeriodicWorkPolicy.UPDATE,
        periodicWorkRequest
    )

}