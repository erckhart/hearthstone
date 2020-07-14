package com.voidx.presentation.list

import androidx.lifecycle.*
import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.GameOption
import com.voidx.domain.cases.ListGameInfoUseCase
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.mapper.Mapper
import com.voidx.presentation.state.State
import com.voidx.presentation.util.disposedBy
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListGameInfoViewModel(
    private val listUseCase: ListGameInfoUseCase,
    private val mainThreadScheduler: Scheduler,
    private val mapper: Mapper<GameOption, GameOptionDTO>
) : ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    private val state: MutableLiveData<State> = MutableLiveData()
    private val data: MutableLiveData<List<GameOptionDTO>> = MutableLiveData()

    fun state(): LiveData<State> = state

    fun data(): LiveData<List<GameOptionDTO>> = data

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun load() {
        state.postValue(State.Loading)

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
            .disposedBy(disposables)
    }

    private fun handleOnError(error: DataError) {
        state.postValue(State.Error(error))
    }

    private fun handleOnSuccess(items: List<GameOption>) {
        if (items.isEmpty()) {
            state.postValue(State.Empty)
        } else {
            val options = items.map {
                mapper.map(it)
            }
            data.postValue(options)
            state.postValue(State.Success)
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}