package com.clp3z.xapotestapp.sections.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.model.RepositoryItemQuery
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
class HomeModel(repository: HomeRepository)
    : GenericModel<HomeRepository>(repository) {

    private var _githubRepositories = MutableLiveData<List<RepositoryItemQuery>>()
    val githubRepositories: LiveData<List<RepositoryItemQuery>>
        get() = _githubRepositories


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