package com.voidx.domain.cases

import com.voidx.data.DataResult
import com.voidx.data.model.GameOption
import io.reactivex.rxjava3.core.Single

interface ListGameInfoUseCase {

    fun getGameInfo(): Single<DataResult<List<GameOption>>>

}