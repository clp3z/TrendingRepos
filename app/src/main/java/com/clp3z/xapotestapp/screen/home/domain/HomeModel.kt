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

        viewState.value = DOWNLOADING

        repositoryStateObserver = Observer<RepositoryState> { state ->
            when (state) {
                DATA_UPDATED_FROM_NETWORK -> viewState.value = ACTIVE

                DATA_UPDATED_FROM_DATABASE -> viewState.value = NO_INTERNET_SNACKBAR

                DATA_EMPTY -> viewState.value = NO_INTERNET

                else -> viewState.value = UNKNOWN_ERROR
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