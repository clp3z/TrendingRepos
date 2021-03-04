package com.clp3z.xapotestapp.base.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.base.general.HomeViewState
import com.clp3z.xapotestapp.base.general.HomeViewState.*
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
        textView.visibility = View.INVISIBLE
    else {
        textView.visibility = if (value.isNotEmpty()) View.VISIBLE else View.INVISIBLE
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
fun bindRecyclerView(recyclerView: RecyclerView, itemsList: MutableList<RepositoryItemQuery>?) {
    val adapter = recyclerView.adapter as RepositoryAdapter
    adapter.submitList(itemsList)
}

// Maybe change from viewState to messageState
@BindingAdapter("app:viewState")
fun bindViewState(view: View, viewState: HomeViewState) {
    when (viewState) {
        INITIAL_DOWNLOAD ->
            view.visibility = if (view.id == R.id.messageDownloading) View.VISIBLE else View.GONE

        UNKNOWN_ERROR ->
            view.visibility = if (view.id == R.id.messageError) View.VISIBLE else View.GONE

        NO_INTERNET ->
            view.visibility = if (view.id == R.id.messageNoConnection) View.VISIBLE else View.GONE

        DOWNLOADING ->
            view.visibility = if (view.id == R.id.loading) View.VISIBLE else View.GONE

        // ACTIVE
        else -> view.visibility = View.GONE
    }
}


// Maybe name is as snackBarState
// TODO: handle NO_INTERNET_SNACKBAR