package com.clp3z.xapotestapp.sections.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.model.RepositoryQuery
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryModel(repositoryDAO: RepositoryDAO)
    : GenericModel<RepositoryDAO>(repositoryDAO) {

    private val _githubRepository = MutableLiveData<RepositoryQuery>()
    val githubRepository: LiveData<RepositoryQuery> get() =  _githubRepository


    override fun fetch() {
        uiScope.launch {
            _githubRepository.value = dataLayer.getRepositoryById()
        }
    }
}