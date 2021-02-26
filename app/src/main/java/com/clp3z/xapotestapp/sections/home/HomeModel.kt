package com.clp3z.xapotestapp.sections.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.repository.database.client.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.general.*
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.base.interfaces.Listener
import com.clp3z.xapotestapp.repository.database.entity.Repository
import com.clp3z.xapotestapp.repository.network.RepositoriesRequest
import com.clp3z.xapotestapp.repository.network.schema.RepositoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 10/20/20
 */
class HomeModel(
    localDatabase: LocalDatabaseDAO,
    application: Application,
):
    GenericModel<Application>(localDatabase, application),
    Listener.OnServerResponseListener<RepositoriesResponse> {

    /**
     * Webservice request to retrieve a page of repositories
     */
    private lateinit var request: RepositoriesRequest

    /**
     * Observable repository list, on proper format. Ready to insert on database
     */
    private var _repositoryList = MutableLiveData<List<Repository>>()
    val repositoryList: LiveData<List<Repository>>
        get() = _repositoryList

    /**
     * Update HomeFragment state
     */
    private val _state = MutableLiveData<ModelState>()
    val state: LiveData<ModelState>
        get() = _state


    init {
        tag = javaClass.simpleName
        logger = Logger(tag)

        fetch()
    }

    override fun fetch() {
        _state.value = ModelState.LOADING
        fetch(false, 1)
    }

    fun fetch(onPagination: Boolean, page: Int) {
        if (isInternetAvailable(param)) {
            request = RepositoriesRequest(page, REPOSITORIES_REQUEST, this)
            request.performServerRequest()

            if (onPagination) {
                _state.value = ModelState.DOWNLOADING
            }

        } else {
            _state.value = ModelState.NO_INTERNET
        }
    }

    override fun onServerResponse(response: RepositoriesResponse?, returnCode: Int) {
        when (returnCode) {
            REPOSITORIES_REQUEST -> {
                _repositoryList.value = getRepositoryList(response!!.items)
            }

            else -> {
                // TODO: Manage server error code
                logger.logError("onServerResponse", "Error with code: ")

                _state.value = ModelState.ERROR
            }
        }
    }

    suspend fun insertAll(repositories: List<Repository>) {
        return withContext(Dispatchers.IO) {
            localDatabase.insertAll(repositories)
        }
    }

    fun getRepositories() =
        localDatabase.getRepositories()


    fun setStateValue(state: ModelState) {
        _state.value = state
    }
}