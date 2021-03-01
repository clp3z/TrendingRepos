package com.clp3z.xapotestapp.base.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 03/1/21
 */

@BindingAdapter("app:isVisible")
fun setViewVisibility(view: View, isVisible: Boolean?) {
    if (isVisible == null)
        view.visibility = View.GONE
    else
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:text")
fun setTextViewVisibility(textView: TextView, value: String?) {
    if (value == null)
        textView.visibility = View.GONE
    else {
        textView.visibility = if (value.isNotEmpty()) View.VISIBLE else View.GONE
        textView.text = value
    }
}

@BindingAdapter(value = ["app:imageUrl", "app:placeholder", "app:errorPlaceholder"], requireAll = true)
fun setImage(imageView: ImageView, url: String?, placeholder: Drawable, errorPlaceholder: Drawable) {
    url?.let {
        if (url.isNotEmpty())
            Picasso.get()
                .load(url)
                .placeholder(placeholder)
                .error(errorPlaceholder)
                .into(imageView)
    }
}

@BindingAdapter(value = ["app:imageUrl", "app:placeholder"], requireAll = true)
fun setImage(imageView: ImageView, url: String?, placeholder: Drawable) {
    url?.let {
        if (url.isNotEmpty())
            Picasso.get()
                .load(url)
                .placeholder(placeholder)
                .into(imageView)
    }
}

@BindingAdapter("app:imageUrl")
fun setImage(imageView: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty())
            Picasso.get()
                .load(url)
                .into(imageView)
    }
}
