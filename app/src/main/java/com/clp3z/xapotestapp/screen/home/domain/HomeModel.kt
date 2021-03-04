package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.clp3z.xapotestapp.base.general.HomeViewState
import com.clp3z.xapotestapp.base.general.HomeViewState.*
import com.clp3z.xapotestapp.base.general.RepositoryState
import com.clp3z.xapotestapp.base.general.RepositoryState.*
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class HomeModel(
    repository: HomeRepository
):
    GenericModel<HomeRepository>(repository) {

    val viewState = MutableLiveData<HomeViewState>()

    private lateinit var repositoryStateObserver: Observer<RepositoryState>

    lateinit var repositories: MutableLiveData<List<RepositoryItemQuery>>

    init {
        fetch()
    }

    override fun fetch() {

        viewState.value = INITIAL_DOWNLOAD

        repositoryStateObserver = Observer<RepositoryState> { state ->
            when (state) {

                DATA_DOWNLOADING -> viewState.value = DOWNLOADING

                DATA_UPDATED_FROM_NETWORK -> viewState.value = ACTIVE

                DATA_UPDATED_FROM_DATABASE -> viewState.value = NO_INTERNET_SNACKBAR

                DATA_EMPTY_REQUEST_ERROR -> viewState.value = UNKNOWN_ERROR

                DATA_ERROR_WITH_DATA -> viewState.value = SUBSEQUENT_UNKNOWN_ERROR

                // DATA_EMPTY_NO_INTERNET
                else -> viewState.value = NO_INTERNET
            }
        }

        dataLayer.repositoryState.observeForever(repositoryStateObserver)

        repositories = dataLayer.repositories
    }

    override fun onCleared() {
        dataLayer.repositoryState.removeObserver(repositoryStateObserver)
        dataLayer.onCleared()
        super.onCleared()
    }
}