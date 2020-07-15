package com.voidx.presentation.dto

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.presentation.BR

class ResultDTO: BaseObservable() {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var image: String = ""
        set(value) {
            field = value
//            notifyPropertyChanged(BR.image)
        }

}