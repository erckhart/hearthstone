package com.voidx.data.repository.impl

import com.voidx.data.DataResult
import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import com.voidx.data.repository.InfoDataSource
import io.reactivex.rxjava3.core.Single

internal class InfoRepository(private val remoteDataSource: InfoDataSource): InfoDataSource {

    override fun getGameInfo(): Single<DataResult<GameInfo>> =
        remoteDataSource.getGameInfo()

    override fun getCardsBy(category: String, filter: String): Single<DataResult<List<Card>>> =
        remoteDataSource.getCardsBy(category, filter)

}