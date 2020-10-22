package com.clp3z.xapotestapp.base.factory

import android.app.Application

/**
 * Created by Clelia López on 10/19/20
 */
class ViewModelFactoryBuilder(
    private var application: Application
) {

    /**
     * Creates ViewModelFactory with expected params
     */
    fun build() = ViewModelFactory(application)
}