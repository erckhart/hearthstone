package com.voidx.presentation.mapper

import com.voidx.data.model.Card
import com.voidx.presentation.dto.CardInfoDTO

class CardMapToCardDto: Mapper<Card, CardInfoDTO> {

    override fun map(from: Card): CardInfoDTO {
        return CardInfoDTO()
    }

}