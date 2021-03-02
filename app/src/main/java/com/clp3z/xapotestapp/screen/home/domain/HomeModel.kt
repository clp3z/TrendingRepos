package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.LiveData
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

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> get() = _viewState

    private lateinit var repositoryStateObserver: Observer<RepositoryState>

    lateinit var repositories: MutableLiveData<List<RepositoryItemQuery>>


    override fun fetch() {

        _viewState.value = DOWNLOADING

        repositoryStateObserver = Observer<RepositoryState> { state ->
            when (state) {
                DATA_UPDATED_FROM_NETWORK -> _viewState.value = ACTIVE

                DATA_UPDATED_FROM_DATABASE -> _viewState.value = NO_INTERNET_SNACKBAR

                DATA_EMPTY -> _viewState.value = NO_INTERNET

                else -> _viewState.value = UNKNOWN_ERROR
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