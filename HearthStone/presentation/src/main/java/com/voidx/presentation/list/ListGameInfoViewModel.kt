package com.voidx.presentation.list

import androidx.lifecycle.*
import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.GameOption
import com.voidx.domain.cases.ListGameInfoUseCase
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.mapper.Mapper
import com.voidx.presentation.state.State
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class ListGameInfoViewModel(
    private val listUseCase: ListGameInfoUseCase,
    private val mainThreadScheduler: Scheduler,
    private val mapper: Mapper<GameOption, GameOptionDTO>
) : ViewModel() {

    private val state: MutableLiveData<State> = MutableLiveData()

    fun state(): LiveData<State> = state

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun load() {
        listUseCase
            .getGameInfo()
            .observeOn(mainThreadScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                when (result) {
                    is DataResult.OnSuccess -> handleOnSuccess(result.data)
                    is DataResult.OnError -> handleOnError(result.error)
                }
            }
            .dispose()
    }

    private fun handleOnError(error: DataError) {
        state.postValue(State.Error(error))
    }

    private fun handleOnSuccess(data: List<GameOption>) {
        if (data.isEmpty()) {
            state.postValue(State.Empty)
        } else {
            val options = data.map {
                mapper.map(it)
            }
            state.postValue(State.Success(options))
        }
    }

}