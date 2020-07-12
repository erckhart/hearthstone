package com.voidx.data

import com.voidx.data.error.DataError

sealed class DataResult<T> {

    data class OnSuccess<T>(val data: T) : DataResult<T>()

    data class OnError<T>(val error: DataError) : DataResult<T>()
}