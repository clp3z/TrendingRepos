package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericRepository
import com.clp3z.xapotestapp.base.util.getRepositoryList
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

    init {
        fetch()
    }

    override fun fetch() {
        fetchRepositories(currentPage)
    }

    private fun fetchRepositories(page: Int) {
        uiScope.launch {
            // TODO: show download dialog
            try {

                val result = networkRequest.getRepositories(page)

                when (result) {
                    is HomeNetworkRequest.Result.Success -> {

                        // Transform
                        val values = getRepositoryList(result.repositories)

                        // Insert
                        dao.insertAll(values)

                        // Expose
                        repositories.value = dao.queryRepositories()
                    }

                    is HomeNetworkRequest.Result.Failure -> onFetchFailed()
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