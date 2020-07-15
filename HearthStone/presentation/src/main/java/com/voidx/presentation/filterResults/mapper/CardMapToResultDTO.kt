package com.voidx.presentation.filterResults.mapper

import com.voidx.data.model.Card
import com.voidx.presentation.dto.ResultDTO
import com.voidx.presentation.mapper.Mapper

class CardMapToResultDTO: Mapper<Card, ResultDTO> {

    override fun map(from: Card): ResultDTO {
        return from.let {
            ResultDTO().apply {
                title = it.name
                image = getCardImage(it)
            }
        }
    }

    private fun getCardImage(card: Card): String {
        if (card.imgGold.isNullOrBlank().not()) {
            return card.imgGold ?: ""
        } else if (card.img.isNullOrBlank().not()) {
            return card.img ?: ""
        }

        return ""
    }

}