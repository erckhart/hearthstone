import com.google.gson.JsonArray
import com.google.gson.JsonObject

object GameInfoTestFactory {

    fun mockServerResponse(key: String, values: List<String>): JsonObject {
        val valuesJson = values.let {
            val array = JsonArray()
            it.forEach { element -> array.add(element) }
            return@let array
        }

        val json = JsonObject()
        json.add(key, valuesJson)

        return json
    }

}