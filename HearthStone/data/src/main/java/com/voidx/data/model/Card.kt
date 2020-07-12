package com.voidx.data.model

import com.google.gson.annotations.SerializedName

data class Card(

    @SerializedName("cardId")
    val cardId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("cardSet")
    val cardSet: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("faction")
    val faction: String,

    @SerializedName("rarity")
    val rarity: String,

    @SerializedName("cost")
    val cost: Int,

    @SerializedName("attack")
    val attack: Int,

    @SerializedName("health")
    val health: Int,

    @SerializedName("text")
    val text: String,

    @SerializedName("flavor")
    val flavor: String,

    @SerializedName("artist")
    val artist: String,

    @SerializedName("collectible")
    val collectible: Boolean,

    @SerializedName("elite")
    val elite: Boolean,

    @SerializedName("race")
    val race: String,

    @SerializedName("img")
    val img: String,

    @SerializedName("imgGold")
    val imgGold: String,

    @SerializedName("locale")
    val locale: String
)
