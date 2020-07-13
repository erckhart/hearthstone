package com.voidx.presentation.dto

import android.graphics.Color
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlin.random.Random

class GameOptionDTO : BaseObservable() {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
//            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var color: Int = -1
        set(value) {
            field = value
//            notifyPropertyChanged(BR.id)
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