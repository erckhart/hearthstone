package com.voidx.data.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.voidx.data.model.GameInfo
import java.lang.reflect.Type

class GameInfoDeserializer: JsonDeserializer<GameInfo> {

    override fun deserialize(
        jsonElement: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GameInfo {
//        TODO("check jsonElement in way to extract key and value")
        return GameInfo("", emptyList())
    }
}