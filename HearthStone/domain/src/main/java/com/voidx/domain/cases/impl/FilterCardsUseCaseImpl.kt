package com.voidx.domain.cases.impl

import com.voidx.data.DataResult
import com.voidx.data.model.Card
import com.voidx.data.repository.InfoDataSource
import com.voidx.domain.cases.FilterCardsUseCase
import io.reactivex.rxjava3.core.Single

class FilterCardsUseCaseImpl(private val repository: InfoDataSource) : FilterCardsUseCase {

    override fun getCardsBy(category: String, keyword: String): Single<DataResult<List<Card>>> =
        repository.getCardsBy(category.toLowerCase(), keyword)

}