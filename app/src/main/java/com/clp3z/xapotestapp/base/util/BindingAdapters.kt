package com.clp3z.xapotestapp.base.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.screen.home.presentation.RepositoryAdapter
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 03/1/21
 */

@BindingAdapter("app:isVisible")
fun bindViewVisibility(view: View, isVisible: Boolean?) {
    if (isVisible == null)
        view.visibility = View.GONE
    else
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:text")
fun bindTextViewVisibility(textView: TextView, value: String?) {
    if (value == null)
        textView.visibility = View.GONE
    else {
        textView.visibility = if (value.isNotEmpty()) View.VISIBLE else View.GONE
        textView.text = value
    }
}

@BindingAdapter(value = ["app:imageUrl", "app:placeholder"], requireAll = true)
fun bindImage(imageView: ImageView, url: String?, placeholder: Drawable) {
    url?.let {
        if (url.isNotEmpty())
            Picasso.get()
                .load(url)
                .placeholder(placeholder)
                .into(imageView)
    }
}

@BindingAdapter("app:itemsList")
fun bindRecyclerView(recyclerView: RecyclerView, itemsList: List<RepositoryItemQuery>?) {
    val adapter = recyclerView.adapter as RepositoryAdapter
    adapter.submitList(itemsList)
}
