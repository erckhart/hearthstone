package com.voidx.domain.cases.impl

import com.voidx.data.DataResult
import com.voidx.data.model.GameInfo
import com.voidx.data.model.GameOption
import com.voidx.data.repository.InfoDataSource
import com.voidx.domain.cases.ListGameInfoUseCase
import io.reactivex.rxjava3.core.Single

internal class ListGameInfoUseCaseImpl(private var repository: InfoDataSource) :
    ListGameInfoUseCase {

    override fun getGameInfo(): Single<DataResult<List<GameOption>>> =
        repository
            .getGameInfo()
            .map { handleSuccess(it) }

    private fun handleSuccess(result: DataResult<GameInfo>): DataResult<List<GameOption>> {
        return when(result) {
            is DataResult.OnSuccess -> DataResult.OnSuccess(result.data.options)
            is DataResult.OnError -> DataResult.OnError(result.error)
        }
    }

}