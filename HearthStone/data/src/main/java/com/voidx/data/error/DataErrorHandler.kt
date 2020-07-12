package com.voidx.data.error

interface DataErrorHandler {

    fun getError(throwable: Throwable): DataError

}