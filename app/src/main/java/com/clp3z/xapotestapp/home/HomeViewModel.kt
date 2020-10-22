package com.clp3z.xapotestapp.home

import android.app.Application
import com.clp3z.xapotestapp.base.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.ModelState
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 10/21/20
 */
class HomeViewModel(
    application: Application,
    database: LocalDatabaseDAO
):
    GenericViewModel<HomeModel>(application, HomeModel(database, application)) {

    /**
     * Retrieves repositories and updates interface via LiveData
     */
    val repositories = model.getRepositories()

    /**
     * Current page on web service
     */
    private var page = 1


    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
    }


    override fun fetch() {
        model.repositoryList.observeForever { repositoryList ->
            uiScope.launch {
                model.insertAll(repositoryList)
            }

            model.setStateValue(ModelState.AVAILABLE)
        }
    }

    fun fetch(onPagination: Boolean) {
        page += 1
        model.fetch(onPagination, this.page)

        logger.log("fetch", "It's fetching on page = $page")
    }

    override fun handleClick(id: Int) {
        // Does nothing
    }

    fun getState() = model.state
}