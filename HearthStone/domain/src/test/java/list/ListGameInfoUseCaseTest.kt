package list

import com.voidx.data.*
import com.voidx.data.error.DataError
import com.voidx.data.repository.InfoDataSource
import com.voidx.domain.cases.ListGameInfoUseCase
import com.voidx.domain.cases.impl.ListGameInfoUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import list.ListGameInfoTestMockFactory.mockErrorNoApiKey
import list.ListGameInfoTestMockFactory.mockGameInfo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import util.RxImmediateSchedulerRule
import util.isNotNull

class ListGameInfoUseCaseTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    private var repository: InfoDataSource = mockk(relaxed = true)

    private lateinit var useCase: ListGameInfoUseCase

    @Before
    fun setup() {
        useCase = ListGameInfoUseCaseImpl(repository)
    }

    @Test
    fun `test successfully list game options`() {

        every { repository.getGameInfo() } returns mockGameInfo()

        useCase
            .getGameInfo()
            .test()
            .assertNoErrors()
            .assertValue { it.isSuccess() }
            .assertValue { it.value().isNotNull() }
            .assertValue { it.value()!!.size == 10 }
    }

    @Test
    fun `test error with no api key`() {


        every { repository.getGameInfo() } returns mockErrorNoApiKey()

        useCase
            .getGameInfo()
            .test()
            .assertNoErrors()
            .assertValue { it.isError() }
            .assertValue { it.error().isNotNull() }
            .assertValue { it.error() == DataError.ApiDataError.NoApiKeyDataError }
    }

}