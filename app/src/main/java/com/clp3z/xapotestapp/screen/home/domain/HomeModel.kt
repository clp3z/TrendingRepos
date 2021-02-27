package com.clp3z.xapotestapp.screen.home.domain

import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class HomeModel(
    repository: HomeRepository
):
    GenericModel<HomeRepository>(repository) {

    lateinit var repositories: MutableLiveData<List<RepositoryItemQuery>>

    override fun fetch() {
       repositories = dataLayer.repositories
    }

    override fun onCleared() {
        dataLayer.onCleared()
        super.onCleared()
    }
}