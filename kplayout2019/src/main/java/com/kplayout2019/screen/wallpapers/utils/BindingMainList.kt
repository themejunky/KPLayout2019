package com.kplayout2019.screen.wallpapers.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


@BindingAdapter(value = ["picture_url"], requireAll = false)
fun setOurPictureToImageView(mImageView: ImageView, mUrl: String) {
    if (!mUrl.isEmpty() && mUrl.isNotEmpty()) {
        Glide.with(mImageView.context).load(mUrl).into(mImageView)
    }
}


