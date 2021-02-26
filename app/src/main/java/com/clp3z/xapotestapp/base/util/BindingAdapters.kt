package com.clp3z.xapotestapp.base.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.getproperly.properlyv2.classes.imagehandling.ProperlyHelper
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 12/4/20
 */

@BindingAdapter("app:visibility")
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

@BindingAdapter("app:imageUrl")
fun setImage(imageView: ImageView, url: String?) {
    if (url != null) {
        val context = imageView.context
        if (url.isNotEmpty()) {
            imageView.setImageBitmap(null)
            ProperlyHelper.getPictureFile(context, url, ProperlyHelper.IMG_MIDDLE) { success, file ->
                if (success)
                    Picasso.with(context).load(file).into(imageView)
            }
        }
    }
}