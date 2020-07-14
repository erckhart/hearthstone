package com.voidx.presentation

import com.voidx.data.model.GameOption
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.list.ListGameInfoViewModel
import com.voidx.presentation.list.mapper.GameOptionMapToGameOptionDto
import com.voidx.presentation.mapper.Mapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import useCaseModule

val module = module {

    factory<Mapper<GameOption, GameOptionDTO>> {
        GameOptionMapToGameOptionDto()
    }

    viewModel {
        ListGameInfoViewModel(get(), get(), get())
    }

}

val presentationModule = listOf(module) + useCaseModule