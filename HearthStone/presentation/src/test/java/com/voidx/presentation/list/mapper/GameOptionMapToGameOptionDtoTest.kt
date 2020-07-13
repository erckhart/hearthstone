package com.voidx.presentation.list.mapper

import com.voidx.data.model.GameOption
import junit.framework.Assert.assertEquals
import org.junit.Test

class GameOptionMapToGameOptionDtoTest {

    @Test
    fun `test map game option to game option dto`() {

        val mapper = GameOptionMapToGameOptionDto()

        val gameOption = GameOption("HearthStone", emptyList())

        val gameOptionDto = mapper.map(gameOption)

        assertEquals(gameOption.title, gameOptionDto.title)

    }

}