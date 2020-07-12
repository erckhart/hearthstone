package com.voidx.data.repository

import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import io.reactivex.rxjava3.core.Single

interface InfoDataSource {

    fun getGameInfo(): Single<GameInfo>

    fun getCardsBy(category: String, filter: String): Single<List<Card>>

}