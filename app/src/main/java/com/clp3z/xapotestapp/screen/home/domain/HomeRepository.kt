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
import com.clp3z.xapotestapp.base.util.toRepositoryItemQuery
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.preference.RepositoryPreference
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
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


    init {
        tableStateObserver = Observer<Boolean> {
            isRepositoryTableEmpty = it
        }
        tableStateLiveDate.observeForever(tableStateObserver)

        this.fetch()
    }

    override fun fetch() {
        fetchRepositories(currentPage)
    }

    private fun fetchRepositories(page: Int) {
        repositoryScope.launch {

            when {
                isInternetAvailable -> {

                    // Request
                    val resultList = networkRequest.getRepositories(page)

                    if (resultList != null) {

                        // Transform and Expose
                        repositories.value = resultList
                            .map { it.toRepository() }
                            .map { it.toRepositoryItemQuery() }

                        // Insert on background
                        dao.insertAll(resultList
                            .map { it.toRepository() })

                        // Notify update
                        _repositoryState.value = DATA_UPDATED_FROM_NETWORK

                        // Update preference
                        repositoryPreference.update(false)

                    } else {

                        // Unknown error while performing network request
                        _repositoryState.value = DATA_ERROR
                    }
                }

                !isInternetAvailable && isRepositoryTableEmpty -> {

                    // Notify that there is no data
                    _repositoryState.value = DATA_EMPTY
                }

                !isInternetAvailable && !isRepositoryTableEmpty -> {

                    // Query and expose
                    repositories.value = dao.queryRepositories()

                    // Notify update, but no internet
                    _repositoryState.value = DATA_UPDATED_FROM_DATABASE
                }
            }
        }
        currentPage += 1
    }

    override fun onCleared() {
        tableStateLiveDate.removeObserver(tableStateObserver)
        super.onCleared()
    }
}