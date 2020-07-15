package com.voidx.presentation.filterResult

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidx.data.model.Card
import com.voidx.domain.cases.FilterCardsUseCase
import com.voidx.presentation.dto.ResultDTO
import com.voidx.presentation.filterResult.FilterResultsViewModelTestMockFactory.mockSuccessFilter
import com.voidx.presentation.filterResults.FilterResultsViewModel
import com.voidx.presentation.mapper.Mapper
import com.voidx.presentation.state.State
import com.voidx.presentation.util.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class FilterResultsViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: FilterCardsUseCase = mockk(relaxed = true)
    private var mapper: Mapper<Card, ResultDTO> = mockk(relaxed = true)

    @Test
    fun `test successfully load filter results`() {

        every { useCase.getCardsBy(any(), any()) } returns mockSuccessFilter()

        val viewModel =
            FilterResultsViewModel("category", "type", AndroidSchedulers.mainThread(), mapper, useCase)

        viewModel.load()

        verify(exactly = 1) { useCase.getCardsBy(any(), any()) }


        Assert.assertEquals(State.Success, viewModel.state().value)
    }

}