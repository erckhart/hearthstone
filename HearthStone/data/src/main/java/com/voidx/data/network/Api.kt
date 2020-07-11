package com.voidx.data.network

import com.voidx.data.model.GameInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface Api {

    @GET("info")
    fun listGameInfos(): Single<GameInfo>

}