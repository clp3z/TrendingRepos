package com.clp3z.xapotestapp.screen.home.domain

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericRepository
import com.clp3z.xapotestapp.base.util.isInternetConnectionAvailable
import com.clp3z.xapotestapp.base.util.toRepository
import com.clp3z.xapotestapp.base.util.toRepositoryItemQuery
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
@Suppress("MoveVariableDeclarationIntoWhen")
class HomeRepository(
    application: Application,
    dao: HomeDAO,
    networkRequest: HomeNetworkRequest,
):
    GenericRepository<HomeDAO, HomeNetworkRequest>(
        application,
        dao,
        networkRequest
    ) {

    var repositories = MutableLiveData<List<RepositoryItemQuery>>()

    private var currentPage = 1

    init {
        this.fetch()
    }

    override fun fetch() {
        if (isInternetConnectionAvailable(application))
            fetchRepositories(currentPage)
        else
            onNoInternetConnection()
    }

    private fun fetchRepositories(page: Int) {
        uiScope.launch {
            // TODO: show download dialog

            // Request
            val resultList = networkRequest.getRepositories(page)

            if (resultList != null) {

                // Transform and Expose
                repositories.value = resultList
                    .map { it.toRepository() }
                    .map { it.toRepositoryItemQuery() }

                // Insert on Background
                dao.insertAll(resultList
                    .map { it.toRepository() })

                // data store true

            } else {
                onFetchFailed()
            }
        }
        currentPage += 1
    }

    override fun onFetchFailed() {
        // TODO: report to Business Logic Layer so an appropriate action can be done
    }

    override fun onNoInternetConnection() {
        uiScope.launch {
            repositories.value = dao.queryRepositories()
        }
    }
}