package com.clp3z.xapotestapp.home

import android.app.Application
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.ModelState
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 10/21/20
 */
class HomeViewModel(
    application: Application,
    homeModel: HomeModel
):
    GenericViewModel<HomeModel>(application, homeModel) {

    /**
     * Retrieves repositories and updates interface via LiveData
     */
    val repositories = model.getRepositories()


    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
    }


    override fun fetch() {
        model.repositoryList.observeForever { repositoryList ->
            uiScope.launch {
                model.insertAll(repositoryList)

                logger.log("fetch", "A change was detected while Observing Forever")
            }

            model.setStateValue(ModelState.AVAILABLE)
        }
    }

    override fun handleClick(id: Int) {
        // Does nothing
    }
}