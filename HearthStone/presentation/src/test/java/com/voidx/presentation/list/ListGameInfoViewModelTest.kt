package com.voidx.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidx.data.error.DataError
import com.voidx.data.model.GameOption
import com.voidx.domain.cases.ListGameInfoUseCase
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.list.ListGameInfoViewModelTestMockFactory.mockEmptyGameOptions
import com.voidx.presentation.list.ListGameInfoViewModelTestMockFactory.mockErrorNoApiKey
import com.voidx.presentation.list.ListGameInfoViewModelTestMockFactory.mockGameOptions
import com.voidx.presentation.list.mapper.GameOptionMapToGameOptionDto
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

class ListGameInfoViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var useCase: ListGameInfoUseCase = mockk(relaxed = true)

    private lateinit var mapper: Mapper<GameOption, GameOptionDTO>
    private lateinit var viewModel: ListGameInfoViewModel

    @Before
    fun setup() {
        mapper = GameOptionMapToGameOptionDto()
        viewModel = ListGameInfoViewModel(useCase, AndroidSchedulers.mainThread(), mapper)
    }

    @Test
    fun `test successfully load game options`() {

        every { useCase.getGameInfo() } returns mockGameOptions()

        viewModel.load()

        verify(exactly = 1) { useCase.getGameInfo() }

        assertEquals(State.Success, viewModel.state().value)
        assertEquals(10, viewModel.data().value?.size)
    }

    @Test
    fun `test empty load game options`() {

        every { useCase.getGameInfo() } returns mockEmptyGameOptions()

        viewModel.load()

        verify(exactly = 1) { useCase.getGameInfo() }

        assertEquals(State.Empty, viewModel.state().value)
    }

    @Test
    fun `test error game options`() {

        every { useCase.getGameInfo() } returns mockErrorNoApiKey()

        viewModel.load()

        verify(exactly = 1) { useCase.getGameInfo() }

        assertEquals(State.Error(DataError.ApiDataError.NoApiKeyDataError), viewModel.state().value)
        assertEquals(DataError.ApiDataError.NoApiKeyDataError, (viewModel.state().value as State.Error).error)
    }

}