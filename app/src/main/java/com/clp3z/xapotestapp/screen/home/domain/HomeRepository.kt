package com.clp3z.xapotestapp.screen.home.domain

import com.clp3z.xapotestapp.base.generic.GenericRepository
import com.clp3z.xapotestapp.base.util.getRepositoryList
import kotlinx.coroutines.*

/**
 * Created by Clelia López on 02/26/21
 */
@Suppress("MoveVariableDeclarationIntoWhen")
class HomeRepository(
    dao: HomeDAO,
    request: HomeRequest
):
    GenericRepository<HomeDAO, HomeRequest>(
        dao,
        request
    ) {

    private var currentPage: Int = 1

    // TODO: internet state verification pending

    override fun fetch() {
        fetchRepositories(currentPage)
    }

    private fun fetchRepositories(page: Int) {
        coroutineScope.launch {
            // TODO: show download dialog
            try {

                val result = networkRequest.getRepositories(page)

                when (result) {
                    is HomeRequest.Result.Success -> {
                        val repositories = getRepositoryList(result.repositories)
                        dao.insertAll(repositories)
                    }

                    is HomeRequest.Result.Failure -> onFetchFailed()
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

    suspend fun getRepositories() = dao.queryRepositories()
}