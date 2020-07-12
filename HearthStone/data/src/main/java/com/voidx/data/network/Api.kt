package com.voidx.data.network

import com.voidx.data.model.Card
import com.voidx.data.model.GameInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface Api {

    @GET("info")
    fun listGameInfos(): Single<GameInfo>

    @GET("cards/{category}/{filter}")
    fun listCardsBy(
        @Path("category") category: String,
        @Path("filter") filter: String
    ): Single<List<Card>>

}