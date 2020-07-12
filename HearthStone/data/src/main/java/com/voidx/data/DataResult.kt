package com.voidx.data

import com.voidx.data.error.DataError

sealed class DataResult<T> {

    companion object {

        fun <T> success(data: T): DataResult<T> {
            return DataResult.OnSuccess(data)
        }

        fun <T> error(error: DataError): DataResult<T> {
            return DataResult.OnError(error)
        }
    }

    data class OnSuccess<T>(val data: T) : DataResult<T>()

    data class OnError<T>(val error: DataError) : DataResult<T>()
}

fun <T> DataResult<T>.isSuccess(): Boolean {
    return this is DataResult.OnSuccess
}

fun <T> DataResult<T>.isError(): Boolean {
    return this is DataResult.OnError
}

fun <T> DataResult<T>.value(): T? {
    return when(this) {
        is DataResult.OnSuccess -> this.data
        else -> null
    }
}

fun <T> DataResult<T>.error(): DataError? {
    return when(this) {
        is DataResult.OnError -> this.error
        else -> null
    }
}