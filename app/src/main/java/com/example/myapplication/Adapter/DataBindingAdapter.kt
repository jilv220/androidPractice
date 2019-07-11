package com.example.myapplication.Adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class DataBindingAdapter {

    @BindingAdapter("android:src")
    fun setImageUri(view: ImageView, bitmap: Bitmap) {
        view.setImageBitmap(bitmap)
    }

}