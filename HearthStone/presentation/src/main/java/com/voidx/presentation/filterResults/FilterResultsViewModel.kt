package com.voidx.presentation.filterResults

import androidx.lifecycle.*
import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.Card
import com.voidx.domain.cases.FilterCardsUseCase
import com.voidx.presentation.dto.ResultDTO
import com.voidx.presentation.mapper.Mapper
import com.voidx.presentation.state.State
import com.voidx.presentation.util.disposedBy
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FilterResultsViewModel(
    private val category: String,
    private val type: String,
    private val mainThreadScheduler: Scheduler,
    private val mapper: Mapper<Card, ResultDTO>,
    private val useCase: FilterCardsUseCase
): ViewModel(), LifecycleObserver {

    private val disposables = CompositeDisposable()

    private val state: MutableLiveData<State> = MutableLiveData()
    private val data: MutableLiveData<List<ResultDTO>> = MutableLiveData()

    fun state(): LiveData<State> = state

    fun data(): LiveData<List<ResultDTO>> = data
    fun title(): LiveData<String> = MutableLiveData(category)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun load() {
        state.postValue(State.Loading)

        useCase
            .getCardsBy(category, type)
            .observeOn(mainThreadScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                when (result) {
                    is DataResult.OnSuccess -> handleSuccess(result.data)
                    is DataResult.OnError -> handleError(result.error)
                }
            }
            .disposedBy(disposables)
    }

    private fun handleError(error: DataError) {
        state.postValue(State.Error(error))
    }

    private fun handleSuccess(data: List<Card>) {
        if(data.isEmpty()) {
            state.postValue(State.Empty)
        } else {
            val results = data.map {
                mapper.map(it)
            }

            this.data.postValue(results)
            state.postValue(State.Success)
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}