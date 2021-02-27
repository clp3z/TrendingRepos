package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
class HomeModel(
    repository: HomeRepository
):
    GenericModel<HomeRepository>(repository) {

    private var _githubRepositories = MutableLiveData<List<RepositoryItemQuery>>()
    val githubRepositories: LiveData<List<RepositoryItemQuery>>
        get() = _githubRepositories

    // TODO: Observation needed to know when data is populated

    override fun fetch() {
        uiScope.launch {
             _githubRepositories.value = dataLayer.getRepositories()
        }
    }

    override fun onCleared() {
        dataLayer.onCleared()
        super.onCleared()
    }
}