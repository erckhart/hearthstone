package com.voidx.data.error

sealed class DataError {

    sealed class ApiDataError: DataError() {

        object NoApiKeyDataError: ApiDataError()

        object NoResultsFoundDataError: ApiDataError()

        object ServerMessageDataError: ApiDataError() {
            lateinit var message: String
        }

    }

    object Unknown: DataError()

    object Network: DataError()
}