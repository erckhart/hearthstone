package com.voidx.data.util

import com.voidx.data.DataResult
import com.voidx.data.error.DataErrorHandler

import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.toResult(errorHandler: DataErrorHandler): Single<DataResult<T>> {
    return map {
        DataResult.success(it)
    }.onErrorReturn {
        DataResult.error(errorHandler.getError(it))
    }
}
