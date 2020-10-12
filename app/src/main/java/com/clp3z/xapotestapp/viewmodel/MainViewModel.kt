package com.clp3z.xapotestapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.Listener
import com.clp3z.xapotestapp.base.ModelState
import com.clp3z.xapotestapp.base.REPOSITORIES_REQUEST
import com.clp3z.xapotestapp.base.isInternetAvailable
import com.clp3z.xapotestapp.repository.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.repository.model.Repository
import com.clp3z.xapotestapp.repository.network.RepositoriesRequest
import com.clp3z.xapotestapp.repository.network.RepositoriesResponse
import kotlinx.coroutines.*

/**
 * Created by Clelia López on 10/10/20
 */
class MainViewModel(
    private val database: LocalDatabaseDAO,
    val app: Application
):
    AndroidViewModel(app), Listener.OnServerResponseListener<RepositoriesResponse> {

    // TODO: handle onItemSelected on adapter

    /**
     * Coroutines
     */
    private val viewModelJob: Job
    private val uiScope: CoroutineScope

    /**
     * Retrieves repositories and updates interface via LiveData
     */
    val repositories = database.getRepositories()

    /**
     * Update MainFragment state
     */
    private val _state = MutableLiveData<ModelState>()
    val state: LiveData<ModelState>
        get() = _state

    init {
        viewModelJob = Job()
        uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    }

    fun fetch(onPagination: Boolean = false, page: Int = 1) {
        if (repositories.value?.size == 0 || onPagination) {

            if (isInternetAvailable(app)) {
                val request = RepositoriesRequest(page, REPOSITORIES_REQUEST, this)
                request.performServerRequest()

                _state.value = ModelState.LOADING

            } else {
                _state.value = ModelState.NO_INTERNET
            }

        } else {
            _state.value = ModelState.AVAILABLE
        }
    }

    private suspend fun insert(repository: Repository) {
        return withContext(Dispatchers.IO) {
            database.insert(repository)
        }
    }

    override fun onServerResponse(response: RepositoriesResponse?, returnCode: Int) {
        when (returnCode) {
            REPOSITORIES_REQUEST ->
            {
                uiScope.launch {
                    for (repository in response?.items!!)
                        insert(repository)
                }

                _state.value = ModelState.AVAILABLE
            }

            else -> _state.value = ModelState.ERROR
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}