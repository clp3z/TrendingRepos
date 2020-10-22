package com.clp3z.xapotestapp.base.factory

import android.app.Application

/**
 * Created by Clelia López on 10/19/20
 */
class ViewModelFactoryBuilder(private var application: Application) {

    /**
     * Repository Id for SafeArgs
     */
    private var id: Int = -1

    fun setId(id: Int) = apply { this.id = id }

    /**
     * Creates ViewModelFactory with expected params
     */
    fun build() = ViewModelFactory(application, id)
}