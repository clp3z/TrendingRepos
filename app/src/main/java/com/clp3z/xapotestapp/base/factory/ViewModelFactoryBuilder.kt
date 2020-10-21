package com.clp3z.xapotestapp.base.factory

import android.app.Application
import com.clp3z.xapotestapp.base.generic.GenericModel

/**
 * Created by Clelia López on 10/19/20
 */

// TODO: If this is not going to be useful, delete it!

class ViewModelFactoryBuilder(
    private var application: Application,
    private val model: GenericModel<*>
) {

    /**
     * Creates ViewModelFactory with expected params
     */
    fun build() = ViewModelFactory(application, model)
}