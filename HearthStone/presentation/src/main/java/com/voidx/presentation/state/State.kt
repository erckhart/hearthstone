package com.voidx.presentation.state

import com.voidx.data.DataResult
import com.voidx.data.error.DataError

sealed class State {

    object Loading : State()

    object Empty : State()

    object Success: State()

    data class Error(var error: DataError) : State()

}
