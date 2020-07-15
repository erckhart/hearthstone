package com.voidx.hearthstone.view.glide.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.voidx.hearthstone.R

object BindableGlide {

    @BindingAdapter("url")
    @JvmStatic
    fun loadImageUrl(imageView: ImageView, url: String?) {
        if(url.isNullOrBlank()) {
            imageView.setImageResource(R.drawable.card_back)
            return
        }

        Glide
            .with(imageView)
            .load(url)
            .placeholder(R.drawable.card_back)
            .into(imageView)
    }

}