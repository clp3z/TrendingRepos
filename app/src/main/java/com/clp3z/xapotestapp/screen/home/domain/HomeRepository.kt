package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericRepository
import com.clp3z.xapotestapp.base.util.toRepository
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
@Suppress("MoveVariableDeclarationIntoWhen")
class HomeRepository(
    dao: HomeDAO,
    networkRequest: HomeNetworkRequest
):
    GenericRepository<HomeDAO, HomeNetworkRequest>(
        dao,
        networkRequest
    ) {

    // TODO: internet state verification pending

    var repositories = MutableLiveData<List<RepositoryItemQuery>>()

    private var currentPage = 1

    override fun fetch() {
        fetchRepositories(currentPage)
    }

    private fun fetchRepositories(page: Int) {
        uiScope.launch {
            // TODO: show download dialog
            try {

                // Request
                val result = networkRequest.getRepositories(page)

                if (result != null) {

                    // Transform and Insert
                    dao.insertAll(result.items.map { it.toRepository() })

                    // Expose
                    repositories.value = dao.queryRepositories()

                } else {
                    onFetchFailed()
                }
            } finally {
                // TODO: hide download dialog
            }
        }
        currentPage += 1
    }

    override fun onFetchFailed() {
        // TODO: report to Business Logic Layer so an appropriate action can be done
    }
}