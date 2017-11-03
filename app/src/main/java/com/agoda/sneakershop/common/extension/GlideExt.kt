package com.agoda.sneakershop.common.extension

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.agoda.sneakershop.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.setImageUrl(url: String, @DrawableRes placeholderResId: Int? = R.drawable.placeholder) {
    Glide.with(context)
            .load(url)
            .apply { placeholderResId?.let { placeholder(it) } }
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .crossFade()
            .into(this)
}
