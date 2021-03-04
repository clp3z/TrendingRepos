package com.clp3z.xapotestapp.screen.home.domain

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.clp3z.xapotestapp.base.general.RepositoryState
import com.clp3z.xapotestapp.base.general.RepositoryState.*
import com.clp3z.xapotestapp.base.generic.GenericRepository
import com.clp3z.xapotestapp.base.util.isInternetConnectionAvailable
import com.clp3z.xapotestapp.base.util.toRepository
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.preference.RepositoryPreference
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by Clelia López on 02/26/21
 */

private const val PAGE_SIZE = 10
@Suppress("MoveVariableDeclarationIntoWhen")
class HomeRepository(
    application: Application,
    dao: HomeDAO,
    networkRequest: HomeNetworkRequest,
    private val repositoryPreference: RepositoryPreference
):
    GenericRepository<HomeDAO, HomeNetworkRequest>(
        application,
        dao,
        networkRequest
    ) {

    private val _repositoryState = MutableLiveData<RepositoryState>()
    val repositoryState: LiveData<RepositoryState> get() =  _repositoryState

    var repositories = MutableLiveData<List<RepositoryItemQuery>>()

    private var currentPage = 1

    private var tableStateObserver: Observer<Boolean>

    private val tableStateLiveDate = repositoryPreference.isRepositoryTableEmptyLiveDate

    private val isInternetAvailable = isInternetConnectionAvailable(application)
    private var isRepositoryTableEmpty = true

    private var isFirstCall = true
    var isLoading = false

    init {
        tableStateObserver = Observer<Boolean> {
            isRepositoryTableEmpty = it
            if (isFirstCall) {
                fetchRepositories()
                isFirstCall = false
            }
        }

        tableStateLiveDate.observeForever(tableStateObserver)
    }

    override fun fetch(
        visibleItemCount: Int,
        totalItemCount: Int,
        firstVisibleItemPosition: Int,
    ) {
        if (!isLoading) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE) {

                runBlocking {
                    fetchRepositories()
                }

                isLoading = false
            }
        }
    }

    private fun fetchRepositories() {

        repositoryScope.launch {
            when {
                isInternetAvailable -> {

                    if (currentPage > 1)
                        _repositoryState.value = DATA_DOWNLOADING

                    // Request
                    val resultList = networkRequest.getRepositories(currentPage)

                    if (resultList != null) {

                        // Transform and insert
                        dao.insertAll(resultList
                            .map { it.toRepository() })

                        // Query and expose
                        repositories.value = dao.queryRepositories()

                        // Notify update
                        _repositoryState.value = DATA_UPDATED_FROM_NETWORK

                        // Update preference
                        repositoryPreference.update(false)

                        // Set next page for next request
                        currentPage += 1

                    } else {

                        // Unknown error while performing network request
                        if (isRepositoryTableEmpty)
                            _repositoryState.value = DATA_EMPTY_REQUEST_ERROR
                        else
                            _repositoryState.value = DATA_ERROR_WITH_DATA
                    }
                }

                !isInternetAvailable && isRepositoryTableEmpty -> {

                    // Notify that there is no data
                    _repositoryState.value = DATA_EMPTY_NO_INTERNET
                }

                !isInternetAvailable && !isRepositoryTableEmpty -> {

                    // Query and expose
                    repositories.value = dao.queryRepositories()

                    // Notify update, but no internet
                    _repositoryState.value = DATA_UPDATED_FROM_DATABASE
                }
            }
        }
    }

    override fun onCleared() {
        tableStateLiveDate.removeObserver(tableStateObserver)
        super.onCleared()
    }
}