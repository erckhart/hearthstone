package filter

import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.Card
import io.reactivex.rxjava3.core.Single
import util.TestUtil

object FilterCardsUseCaseTestMockFactory {

    fun mockSuccessFilter(): Single<DataResult<List<Card>>> {
        val list: List<Card> = TestUtil.getObject("filter_cards_200.json")
        return Single.just(DataResult.success(list))
    }

    fun mockNoResultsFound(): Single<DataResult<List<Card>>> {
        return Single.just(DataResult.error(DataError.ApiDataError.NoResultsFoundDataError))
    }

    fun mockApiKeyNotFound(): Single<DataResult<List<Card>>> {
        return Single.just(DataResult.error(DataError.ApiDataError.NoApiKeyDataError))
    }

}