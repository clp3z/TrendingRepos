package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.interfaces.RepositoryMethods
import kotlinx.coroutines.*

/**
 * Created by Clelia López on 02/27/21
 */
abstract class GenericRepository<DAO: GenericDAO<*>, NR: GenericNetworkRequest<*>> (
    protected val dao: DAO,
    protected val networkRequest: NR
):
    RepositoryMethods {

    private val job: Job by lazy {
        Job()
    }

    protected val uiScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + job)
    }

    fun onCleared() {
        job.cancelChildren()
    }
}