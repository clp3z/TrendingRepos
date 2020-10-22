package com.clp3z.xapotestapp.base.general

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.repository.database.Repository
import com.clp3z.xapotestapp.repository.network.RepositoryResponse
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 10/10/20
 */
@Suppress("DEPRECATION")
fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager
            .getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }

    return result
}

@Suppress("FunctionName")
fun repository_FromResponse(response: RepositoryResponse): Repository {
    return Repository(
        response.id,
        response.name,
        response.description,
        response.owner,
        response.forks,
        response.issues,
        response.watchers,
        response.created,
        response.updated,
        response.pushed,
        response.size,
        response.language,
        response.url,
        response.contributorsUrl,
    )
}

fun getRepositoryList(list: List<RepositoryResponse>): List<Repository> {
    val result = ArrayList<Repository>()
    for (item in list) {
        result.add(repository_FromResponse(item))
    }
    return result
}

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = true)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable) {
    Picasso.get()
        .load(url)
        .placeholder(placeHolder)
        .into(imageView)
}