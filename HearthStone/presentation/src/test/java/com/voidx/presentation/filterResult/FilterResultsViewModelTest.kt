package com.voidx.presentation.filterResult

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidx.data.error.DataError
import com.voidx.data.model.Card
import com.voidx.domain.cases.FilterCardsUseCase
import com.voidx.presentation.dto.ResultDTO
import com.voidx.presentation.filterResult.FilterResultsViewModelTestMockFactory.mockEmptyResults
import com.voidx.presentation.filterResult.FilterResultsViewModelTestMockFactory.mockErrorNoApiKey
import com.voidx.presentation.filterResult.FilterResultsViewModelTestMockFactory.mockSuccessFilter
import com.voidx.presentation.filterResults.FilterResultsViewModel
import com.voidx.presentation.filterResults.mapper.CardMapToResultDTO
import com.voidx.presentation.mapper.Mapper
import com.voidx.presentation.state.State
import com.voidx.presentation.util.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterResultsViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: FilterCardsUseCase = mockk(relaxed = true)

    private lateinit var mapper: Mapper<Card, ResultDTO>

    @Before
    fun setup() {
        mapper = CardMapToResultDTO()
    }

    @Test
    fun `test successfully load filter results`() {

        every { useCase.getCardsBy(any(), any()) } returns mockSuccessFilter() //Single.just(DataResult.success(emptyList()))

        val viewModel =
            FilterResultsViewModel("category", "type", AndroidSchedulers.mainThread(), mapper, useCase)

        viewModel.load()

        verify(exactly = 1) { useCase.getCardsBy(any(), any()) }


        assertEquals(State.Success, viewModel.state().value)
        assertEquals(10, viewModel.data().value?.size)
    }

    @Test
    fun `test empty results`() {

        every { useCase.getCardsBy(any(), any()) } returns mockEmptyResults()

        val viewModel =
            FilterResultsViewModel("category", "type", AndroidSchedulers.mainThread(), mapper, useCase)

        viewModel.load()

        verify(exactly = 1) { useCase.getCardsBy(any(), any()) }

        assertEquals(State.Empty, viewModel.state().value)
    }

    @Test
    fun `test error game options`() {

        every { useCase.getCardsBy(any(), any()) } returns mockErrorNoApiKey()

        val viewModel =
            FilterResultsViewModel("category", "type", AndroidSchedulers.mainThread(), mapper, useCase)

        viewModel.load()

        verify(exactly = 1) { useCase.getCardsBy(any(), any()) }

        assertEquals(
            State.Error(DataError.ApiDataError.NoApiKeyDataError),
            viewModel.state().value
        )
        assertEquals(
            DataError.ApiDataError.NoApiKeyDataError,
            (viewModel.state().value as State.Error).error
        )
    }

}