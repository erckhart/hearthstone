package com.voidx.hearthstone.filterResults

import com.voidx.hearthstone.util.createMockResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

sealed class FilterResultsMockFactory {

    object SuccessDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/info" ->
                    createMockResponse(200, "info/game_info_200.json")
                "/cards/classes/Death%20Knight" ->
                    createMockResponse(200, "results/filter_cards_200.json")
                else ->
                    createMockResponse(404)
            }
        }
    }
}