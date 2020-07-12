package com.voidx.data.error.impl

import com.voidx.data.error.DataError
import com.voidx.data.error.DataErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

internal class DataErrorHandlerImpl : DataErrorHandler {

    override fun getError(throwable: Throwable): DataError {
        return when (throwable) {
            is IOException -> DataError.Network
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> DataError.ApiDataError.NoResultsFoundDataError
                    HttpURLConnection.HTTP_UNAUTHORIZED -> DataError.ApiDataError.NoApiKeyDataError
                    else -> DataError.Unknown
                }
            }
            else -> DataError.Unknown
        }
    }
}