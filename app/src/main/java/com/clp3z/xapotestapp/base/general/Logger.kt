package com.clp3z.xapotestapp.base.general

import android.util.Log
import com.clp3z.xapotestapp.R

/**
 * Created by Clelia López on 10/19/20
 */
class Logger(private val TAG: String) {

    /**
     * Properties
     */
    private val APP = R.string.app_name
    private val separator = "=====> "

    private val isDebug = true

    fun log(method: String, message: String) {
        if (isDebug)
            Log.i("$APP - $TAG:", "$separator$method: $message")
    }

    fun logDebug(method: String, message: String) {
        if (isDebug)
            Log.d("$APP - $TAG:", "$separator$method: $message")
    }

    fun logError(method: String, message: String?) {
        if (isDebug)
            Log.e("$APP - $TAG:", "$separator$method: $message")
    }
}