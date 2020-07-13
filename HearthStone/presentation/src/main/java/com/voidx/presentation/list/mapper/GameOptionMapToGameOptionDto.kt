package com.voidx.presentation.list.mapper

import com.voidx.data.model.GameOption
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.mapper.Mapper

internal class GameOptionMapToGameOptionDto:
    Mapper<GameOption, GameOptionDTO> {

    override fun map(from: GameOption): GameOptionDTO {
        return GameOptionDTO().apply {
            title = from.title
        }
    }

}