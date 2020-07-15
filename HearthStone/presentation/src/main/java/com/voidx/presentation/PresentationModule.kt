package com.voidx.presentation

import com.voidx.presentation.filterResults.FilterResultsViewModel
import com.voidx.presentation.filterResults.mapper.CardMapToResultDTO
import com.voidx.presentation.list.ListGameInfoViewModel
import com.voidx.presentation.list.mapper.GameOptionMapToGameOptionDto
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import useCaseModule

val module = module {

    viewModel {
        ListGameInfoViewModel(get(), get(), GameOptionMapToGameOptionDto())
    }

    viewModel { (category: String, type: String) ->
        FilterResultsViewModel(category, type, get(), CardMapToResultDTO(), get())
    }

}

val presentationModule = listOf(module) + useCaseModule