package com.voidx.presentation.list

import com.voidx.data.DataResult
import com.voidx.data.error.DataError
import com.voidx.data.model.GameOption
import io.reactivex.rxjava3.core.Single

object ListGameInfoViewModelTestMockFactory {

    fun mockGameOptions(): Single<DataResult<List<GameOption>>> {

        val options: List<GameOption> = mutableListOf<GameOption>().apply {
            for (i in 1..10) {
                add(
                    GameOption(
                        "hearthStone$i",
                        listOf("Death Knight", "Druid", "Hunter")
                    )
                )
            }
        }

        return Single.just(DataResult.success(options))
    }

    fun mockEmptyGameOptions(): Single<DataResult<List<GameOption>>> {
        return Single.just(DataResult.success(emptyList()))
    }

    fun mockErrorNoApiKey(): Single<DataResult<List<GameOption>>> {
        return Single.just(DataResult.error(DataError.ApiDataError.NoApiKeyDataError))
    }

}