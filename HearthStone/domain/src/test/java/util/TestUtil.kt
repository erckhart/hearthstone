package util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

object TestUtil {

    fun <T> getObject(fileName: String, classType: Class<T>): T {
        val json = getJson(fileName)
        return Gson().fromJson(json, classType)
    }

    fun <T> getObject(fileName: String): T {
        val json = getJson(fileName)
        val type: Type = object : TypeToken<T>() {}.type
        return Gson().fromJson(json, type)
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
}

fun Any?.isNotNull(): Boolean {
    return this != null
}