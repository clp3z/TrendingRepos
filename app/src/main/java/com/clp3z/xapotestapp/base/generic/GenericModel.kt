package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.interfaces.ModelMethods
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by Clelia López on 02/26/21
 */
abstract class GenericModel<T> (
    protected val dataLayer: T
):
    ModelMethods {

    protected lateinit var tagClass: String
    protected lateinit var logger: Logger

    private val job: Job by lazy {
        Job()
    }

    protected val uiScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + job)
    }

    init {
        this.fetch()
    }

    open fun onCleared() {
        job.cancel()
    }
}
