package com.clp3z.xapotestapp.sections.home_old

import android.app.Application
import com.clp3z.xapotestapp.repository.database.client.RepositoryDAO
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.ModelState
import com.clp3z.xapotestapp.base.generic.GenericViewModel

/**
 * Created by Clelia López on 10/21/20
 */
class HomeViewModel(
    application: Application,
    database: RepositoryDAO
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
        tag = javaClass.simpleName
        logger = Logger(tag)
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