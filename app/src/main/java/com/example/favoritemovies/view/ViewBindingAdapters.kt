package com.example.favoritemovies.view

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String) {
    Picasso.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("manageResponse")
fun manageResponse(errorWidget: View, isSuccessful: Boolean) {
    errorWidget.visibility = if (isSuccessful) View.VISIBLE else View.GONE
}
