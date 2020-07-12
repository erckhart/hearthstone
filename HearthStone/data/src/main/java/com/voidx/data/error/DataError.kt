package com.voidx.data.error

sealed class DataError {

    sealed class ApiDataError: DataError() {

        object NoApiKeyDataError: ApiDataError()

        object NoResultsFoundDataError: DataError()

    }

    object Unknown: DataError()

    object Network: DataError()
}