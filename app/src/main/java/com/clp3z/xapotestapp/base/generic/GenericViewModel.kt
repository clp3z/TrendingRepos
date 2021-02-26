package com.clp3z.xapotestapp.base.generic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.interfaces.ViewModelMethods

/**
 * Created by Clelia López on 02/26/21
 */
abstract class GenericViewModel<M: GenericModel<*>>(
    application: Application,
    protected val model: M
):
    AndroidViewModel(application),
    ViewModelMethods {

    protected lateinit var tagClass: String
    protected lateinit var logger: Logger

    init {
        this.fetch()
        this.addModelObservers()
    }

    override fun onCleared() {
        model.onCleared()
        super.onCleared()
    }
}