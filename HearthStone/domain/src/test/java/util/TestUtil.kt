package util

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.nio.charset.StandardCharsets

object TestUtil {

    fun <T> getObject(fileName: String, classType: Class<T>): T {
        val json = getJson(fileName)
        return Gson().fromJson(json, classType)
    }

    fun getJson(fileName: String): String {
        return try {
            val classLoader = ClassLoader.getSystemClassLoader()
            val inputStream = classLoader.getResourceAsStream(fileName)
            val size = inputStream?.available() ?: 0
            val buffer = ByteArray(size)

            inputStream?.read(buffer)
            inputStream?.close()

            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            "{}"
        }
    }

    fun createGenericError(errorCode: Int): HttpException {

        var error = getJson("generic_error.json")
        error = String.format(error, errorCode.toString())

        val responseError = Response.error<Any>(
            errorCode,
            error.toResponseBody("application/json".toMediaTypeOrNull())
        )

        return HttpException(responseError)
    }

}