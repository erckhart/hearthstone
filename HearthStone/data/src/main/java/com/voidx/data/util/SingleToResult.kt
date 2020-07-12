package com.voidx.data.util

import com.voidx.data.DataResult
import com.voidx.data.error.DataErrorHandler

import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.toResult(errorHandler: DataErrorHandler): Single<DataResult<T>> {
    return map {
        handleSuccess(it)
    }.onErrorReturn {
        handleError(it, errorHandler)
    }
}

fun <T> handleError(throwable: Throwable, errorHandler: DataErrorHandler): DataResult<T> {
    return DataResult.OnError(errorHandler.getError(throwable))
}

fun <T> handleSuccess(value: T): DataResult<T> {
    return DataResult.OnSuccess(value)
}
