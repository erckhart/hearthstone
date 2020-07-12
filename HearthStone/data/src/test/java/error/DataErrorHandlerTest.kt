package error

import com.voidx.data.error.DataError
import com.voidx.data.error.DataErrorHandler
import com.voidx.data.error.impl.DataErrorHandlerImpl
import junit.framework.Assert.assertEquals
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection

class DataErrorHandlerTest {

    private lateinit var dataErrorHandler: DataErrorHandler

    private val response = "ResponseBody".toResponseBody("application/json".toMediaType())

    @Before
    fun setup() {
        dataErrorHandler = DataErrorHandlerImpl()
    }

    @Test
    fun `test handle error correctly`() {

        var throwable: Throwable = IOException()
        var dataError = dataErrorHandler.getError(throwable)

        assertEquals(DataError.Network, dataError)

        throwable = UnsupportedOperationException()
        dataError = dataErrorHandler.getError(throwable)

        assertEquals(DataError.Unknown, dataError)

        throwable = HttpException(Response.error<Any>(HttpURLConnection.HTTP_NOT_FOUND, response))
        dataError = dataErrorHandler.getError(throwable)

        assertEquals(DataError.ApiDataError.NoResultsFoundDataError, dataError)

        throwable =
            HttpException(Response.error<Any>(HttpURLConnection.HTTP_UNAUTHORIZED, response))
        dataError = dataErrorHandler.getError(throwable)

        assertEquals(DataError.ApiDataError.NoApiKeyDataError, dataError)

        throwable = HttpException(Response.error<Any>(999, response))
        dataError = dataErrorHandler.getError(throwable)

        assertEquals(DataError.Unknown, dataError)
    }

}