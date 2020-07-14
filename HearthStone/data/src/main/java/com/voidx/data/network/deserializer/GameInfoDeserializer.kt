package com.voidx.data.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.voidx.data.model.GameInfo
import com.voidx.data.model.GameOption
import java.lang.reflect.Type

class GameInfoDeserializer: JsonDeserializer<GameInfo> {

    override fun deserialize(
        jsonElement: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GameInfo {

        val json = jsonElement?.asJsonObject?.let {
            it.remove("patch")
            it.remove("locales")
            it.remove("wild")
            it.remove("standard")
            it
        }

        val options = json?.entrySet()?.map {
            val values = it.value.asJsonArray.map { jsonElement -> jsonElement.asString }
            GameOption(it.key.capitalize(), values)
        } ?: emptyList()

        return GameInfo(options)
    }
}