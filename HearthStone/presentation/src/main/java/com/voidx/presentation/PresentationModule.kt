package com.voidx.presentation

import com.voidx.data.DataSettings
import com.voidx.presentation.dto.ConfigurationDTO
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

    single {
        val configuration = get<ConfigurationDTO>()
        DataSettings(
            configuration.serverUrl,
            configuration.serverHost,
            configuration.apiKey,
            configuration.isDebug
        )
    }

}

val presentationModule = listOf(module) + useCaseModule