package com.clp3z.xapotestapp.sections.home

import com.clp3z.xapotestapp.base.util.getRepositoryList
import kotlinx.coroutines.*

/**
 * Created by Clelia López on 02/26/21
 */
@Suppress("MoveVariableDeclarationIntoWhen")
class HomeRepository(
    private val homeDAO: HomeDAO,
    private val homeRequest: HomeRequest
) {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var currentPage: Int = 1

    // TODO: internet state verification pending

    init {
        fetch()
    }

    private fun fetch() {
        fetchRepositories(currentPage)
    }

    private fun fetchRepositories(page: Int) {
        coroutineScope.launch {
            // TODO: show download dialog
            try {
                val result = homeRequest.getRepositories(page)
                when (result) {

                    is HomeRequest.Result.Success -> {
                        homeDAO.insertAll(getRepositoryList(result.repositories))
                    }

                    is HomeRequest.Result.Failure -> onFetchFailed()
                }
            } finally {
                // TODO: hide download dialog
            }
        }
        currentPage += 1
    }


    private fun onFetchFailed() {
        // TODO: report to Business Logic Layer so an appropriate action can be done
    }

    suspend fun getRepositories() = homeDAO.queryRepositories()

    fun onCleared() {
        coroutineScope.coroutineContext.cancelChildren()
    }
}