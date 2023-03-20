package com.example.weather.data

import com.example.weather.domain.models.errors.ErrorEntity
import com.example.weather.domain.models.errors.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import javax.inject.Inject

class GeneralErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when(throwable.code()) {
                    HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    else -> ErrorEntity.Unknown(throwable.code().toString())
                }
            }
            else -> ErrorEntity.Unknown(throwable.message.toString())
        }
    }
}