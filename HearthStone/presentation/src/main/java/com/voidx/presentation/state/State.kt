package com.voidx.presentation.state

import com.voidx.data.error.DataError

sealed class State {

    object Loading : State()

    object Empty : State()

    data class Error(var error: DataError) : State()

    data class Success<T : Any>(val value: T? = null) : State()
}
