package com.voidx.presentation.filterResult

import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.Card
import com.voidx.presentation.util.TestUtil
import io.reactivex.rxjava3.core.Single

object FilterResultsViewModelTestMockFactory {

    fun mockSuccessFilter(): Single<DataResult<List<Card>>> {
        val list: List<Card> = TestUtil.getObjects("filter_cards_200.json")
        return Single.just(DataResult.success(list))
    }

    fun mockEmptyResults(): Single<DataResult<List<Card>>> {
        return Single.just(DataResult.success(emptyList()))
    }

    fun mockErrorNoApiKey(): Single<DataResult<List<Card>>> {
        return Single.just(DataResult.error(DataError.ApiDataError.NoApiKeyDataError))
    }

}