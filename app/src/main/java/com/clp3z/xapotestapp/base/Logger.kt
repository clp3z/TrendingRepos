package com.clp3z.xapotestapp.base

import android.util.Log
import com.clp3z.xapotestapp.BuildConfig
import com.clp3z.xapotestapp.R

/**
 * Created by Clelia López on 10/10/20
 */
class Logger(private val TAG: String) {

    /**
     * Properties
     */
    private val APP = R.string.app_name
    private val separator = "=====> "

    fun log(method: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.i("$APP - $TAG:", "$separator$method: $message")
    }

    fun logDebug(method: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.d("$APP - $TAG:", "$separator$method: $message")
    }

    fun logError(method: String, message: String?) {
        if (BuildConfig.DEBUG)
            Log.e("$APP - $TAG:", "$separator$method: $message")
    }
}