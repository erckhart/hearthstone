package com.voidx.data.repository

import com.voidx.data.DataResult
import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import io.reactivex.rxjava3.core.Single

interface InfoDataSource {

    fun getGameInfo(): Single<DataResult<GameInfo>>

    fun getCardsBy(category: String, filter: String): Single<DataResult<List<Card>>>

}