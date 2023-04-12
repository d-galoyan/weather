package com.example.weather.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.weather.domain.useCases.weather.UpdateWeatherDataUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class UpdateWeatherWorkerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var updateWeatherDataUseCase: UpdateWeatherDataUseCase

    private lateinit var worker: UpdateWeatherWorker

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val configuration = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
        worker = TestListenableWorkerBuilder<UpdateWeatherWorker>(
            context = context,
            tags = listOf(TAG)
        ).build()
    }

    @After
    fun tearDown() {
        WorkManager.getInstance(ApplicationProvider.getApplicationContext()).cancelAllWorkByTag(TAG)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDoWorkReturnsSuccess() = runTest {
        Mockito.`when`(updateWeatherDataUseCase()).thenReturn(Unit)
        val result = worker.doWork()
        assertThat(result, `is`(Result.success()))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDoWorkReturnsFailure() = runTest {
        val exception = Exception("Failed to update weather")
        Mockito.`when`(updateWeatherDataUseCase()).thenThrow(exception)
        val result = worker.doWork()
        assertThat(result, `is`(Result.failure()))
    }

    @Test
    fun testUpdateWeatherWorkerEnqueuesPeriodicWork() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        updateWeatherWorker(context)
        val workInfos = WorkManager.getInstance(context).getWorkInfosByTag(TAG).get()
        assert(workInfos.size == 1)
    }
}
