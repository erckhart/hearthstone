package filter

import com.voidx.data.error
import com.voidx.data.error.DataError
import com.voidx.data.isError
import com.voidx.data.isSuccess
import com.voidx.data.repository.InfoDataSource
import com.voidx.data.value
import com.voidx.domain.cases.FilterCardsUseCase
import com.voidx.domain.cases.impl.FilterCardsUseCaseImpl
import filter.FilterCardsUseCaseTestMockFactory.mockApiKeyNotFound
import filter.FilterCardsUseCaseTestMockFactory.mockNoResultsFound
import filter.FilterCardsUseCaseTestMockFactory.mockSuccessFilter
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import util.RxImmediateSchedulerRule

internal class FilterCardsUseCaseTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private var repository: InfoDataSource = mockk(relaxed = true)

    private lateinit var useCase: FilterCardsUseCase

    @Before
    fun setup() {
        useCase = FilterCardsUseCaseImpl(repository)
    }

    @Test
    fun `test category is lower case`() {

        val categorySlot = slot<String>()

        every { repository.getCardsBy(capture(categorySlot), any()) } returns mockSuccessFilter()

        useCase
            .getCardsBy("Sets", "keyword")
            .test()
            .assertValue { categorySlot.captured == "sets" }
            .assertValue { it.isSuccess() }
    }

    @Test
    fun `test successfully filtered cards`() {

        every { repository.getCardsBy(any(), any()) } returns mockSuccessFilter()

        useCase
            .getCardsBy("Sets", "keyword")
            .test()
            .assertValue { it.isSuccess() }
            .assertValue { it.value()!!.size == 10 }

    }

    @Test
    fun `test not found cards`() {

        every { repository.getCardsBy(any(), any()) } returns mockNoResultsFound()

        useCase
            .getCardsBy("Sets", "keyword")
            .test()
            .assertNoErrors()
            .assertValue { it.isError() }
            .assertValue { it.error() == DataError.ApiDataError.NoResultsFoundDataError }
    }

    @Test
    fun `test api key error`() {

        every { repository.getCardsBy(any(), any()) } returns mockApiKeyNotFound()

        useCase
            .getCardsBy("Sets", "keyword")
            .test()
            .assertNoErrors()
            .assertValue { it.isError() }
            .assertValue { it.error() == DataError.ApiDataError.NoApiKeyDataError }
    }

}