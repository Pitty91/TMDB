package com.tmdb.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tmdb.R

/**
 * Created by ANDROID on 13/12/17.
 */

@BindingAdapter("bind:image")
fun loadImage(imageView: ImageView, url: String?) {
    // Log.e("CustomBinding", "loadImage: " + url!!)

    if (url != null && url.trim().isNotEmpty()) {
        Glide.with(imageView.context)
                .load(url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView)
    } else {
        imageView.setImageDrawable(null)
    }
}

