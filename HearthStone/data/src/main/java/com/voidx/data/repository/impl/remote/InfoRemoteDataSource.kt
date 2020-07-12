package com.voidx.data.repository.impl.remote

import com.voidx.data.DataResult
import com.voidx.data.error.DataErrorHandler
import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import com.voidx.data.network.Api
import com.voidx.data.repository.InfoDataSource
import com.voidx.data.util.toResult
import io.reactivex.rxjava3.core.Single

internal class InfoRemoteDataSource(private val api: Api, private val errorHandler: DataErrorHandler): InfoDataSource {

    override fun getGameInfo(): Single<DataResult<GameInfo>> =
        api.listGameInfos().toResult(errorHandler)

    override fun getCardsBy(category: String, filter: String): Single<DataResult<List<Card>>> =
        api.listCardsBy(category, filter).toResult(errorHandler)

}