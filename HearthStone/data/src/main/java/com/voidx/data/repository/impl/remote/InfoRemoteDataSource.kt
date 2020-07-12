package com.voidx.data.repository.impl.remote

import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import com.voidx.data.network.Api
import com.voidx.data.repository.InfoDataSource
import io.reactivex.rxjava3.core.Single

internal class InfoRemoteDataSource(private val api: Api): InfoDataSource {

    override fun getGameInfo(): Single<GameInfo> =
        api.listGameInfos()

    override fun getCardsBy(category: String, filter: String): Single<List<Card>> =
        api.listCardsBy(category, filter)

}