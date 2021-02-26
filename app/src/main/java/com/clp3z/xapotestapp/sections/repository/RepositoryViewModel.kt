package com.clp3z.xapotestapp.sections.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import com.clp3z.xapotestapp.model.RepositoryQuery

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryViewModel(
    application: Application,
    repositoryModel: RepositoryModel
):
    GenericViewModel<RepositoryModel>(application, repositoryModel) {


    private var _name = MutableLiveData<String>()
    val name: LiveData<String> = Transformations.map(_name) { it.trim() }

    private var _description = MutableLiveData<String>()
    val description: LiveData<String> = Transformations.map(_description) { it.trim() }

    private var _forks = MutableLiveData<Int>()
    val forks: LiveData<String> = Transformations.map(_forks) { it.toString() }

    private var _watchers = MutableLiveData<Int>()
    val watchers: LiveData<String> = Transformations.map(_watchers) { it.toString() }

    private var _issues = MutableLiveData<Int>()
    val issues: LiveData<String> = Transformations.map(_issues) { it.toString() }

    private var _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> get() = _avatar

    private lateinit var repositoryObserver: Observer<RepositoryQuery>


    override fun fetch() {
        repositoryObserver = Observer<RepositoryQuery> { repository ->
            _name.value = repository.name
            _description.value = repository.description
            _forks.value = repository.forks
            _watchers.value = repository.watchers
            _issues.value = repository.issues
            _avatar.value = repository.owner_avatar
        }
    }

    override fun addModelObservers() {
        model.githubRepository.observeForever(repositoryObserver)
    }

    override fun onCleared() {
        model.githubRepository.removeObserver(repositoryObserver)
        super.onCleared()
    }
}