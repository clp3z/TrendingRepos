package com.clp3z.xapotestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.repository.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.repository.model.Repository
import kotlinx.coroutines.*

/**
 * Created by Clelia López on 10/10/20
 */
class RepositoryViewModel(
    private val database: LocalDatabaseDAO,
    private val id: Int
)
    : ViewModel() {

    // TODO: set avatar with Glide

    /**
     * Coroutines
     */
    private val viewModelJob: Job
    private val uiScope: CoroutineScope

    /**
     * Retrieves repository from database and updates via LiveData
     */
    private var _repository =  MutableLiveData<Repository>()
    val repository: LiveData<Repository>
        get() = _repository

    init {
        viewModelJob = Job()
        uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        fetch()
    }

    private fun fetch() {
        uiScope.launch {
            _repository.value = getRepositoryById()
        }
    }

    private suspend fun getRepositoryById(): Repository {
        return withContext(Dispatchers.IO) {
            database.get(id)
        }
    }
}