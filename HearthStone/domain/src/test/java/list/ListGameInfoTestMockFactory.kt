package list

import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.GameInfo
import com.voidx.data.model.GameOption
import io.reactivex.rxjava3.core.Single
import util.TestUtil

internal object ListGameInfoTestMockFactory {

    fun mockGameInfo(): Single<DataResult<GameInfo>> {

        val options = mutableListOf<GameOption>().apply {
            for (i in 1..10) {
                add(
                    GameOption(
                        "hearthStone$i",
                        listOf("Death Knight", "Druid", "Hunter")
                    )
                )
            }
        }

        return Single.just(DataResult.success(GameInfo(options)))
    }

    fun mockErrorNoApiKey(): Single<DataResult<GameInfo>> {
        return Single.just(DataResult.error(DataError.ApiDataError.NoApiKeyDataError))
    }

}