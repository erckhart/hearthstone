package com.voidx.presentation.dto

import android.graphics.Color
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.presentation.BR
import kotlin.random.Random

class GameOptionDTO : BaseObservable() {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var items: List<GameOptionItemDTO> = emptyList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.items)
        }

}

class GameOptionItemDTO : BaseObservable() {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var color: Int = -1
        set(value) {
            field = value
            notifyPropertyChanged(BR.color)
        }

    fun randomizeColor() {
        color = Color.argb(
            255,
            Random.nextInt(255),
            Random.nextInt(255),
            Random.nextInt(255)
        )
    }

}