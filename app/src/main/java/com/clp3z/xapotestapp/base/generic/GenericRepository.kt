package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.interfaces.RepositoryMethods
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

/**
 * Created by Clelia López on 02/27/21
 */
abstract class GenericRepository<DAO: GenericDAO<*>, NR: GenericNetworkRequest<*>> (
    protected val dao: DAO,
    protected val networkRequest: NR
):
    RepositoryMethods {

    protected val coroutineScope = CoroutineScope(SupervisorJob()
        + Dispatchers.Main.immediate)

    init {
        this.fetch()
    }

    fun onCleared() {
        coroutineScope.coroutineContext.cancelChildren()
    }
}