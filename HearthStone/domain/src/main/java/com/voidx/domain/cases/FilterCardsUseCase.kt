package com.voidx.domain.cases

import com.voidx.data.DataResult
import com.voidx.data.model.Card
import io.reactivex.rxjava3.core.Single

interface FilterCardsUseCase {

    fun getCardsBy(category: String, keyword: String): Single<DataResult<List<Card>>>

}