package com.clp3z.xapotestapp.base.general

import android.util.Log
import com.clp3z.xapotestapp.R

/**
 * Created by Clelia López on 10/19/20
 */
@Suppress("unused")
class Logger(private val tag: String) {

    /**
     * Properties
     */
    private val app = R.string.app_name
    private val separator = "=====> "

    private val isDebug = true

    fun log(method: String, message: String) {
        if (isDebug)
            Log.i("$app - $tag:", "$separator$method: $message")
    }

    fun logDebug(method: String, message: String) {
        if (isDebug)
            Log.d("$app - $tag:", "$separator$method: $message")
    }

    fun logError(method: String, message: String?) {
        if (isDebug)
            Log.e("$app - $tag:", "$separator$method: $message")
    }
}