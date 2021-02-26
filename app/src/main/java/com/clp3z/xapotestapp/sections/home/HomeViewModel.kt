package com.clp3z.xapotestapp.sections.home

import android.app.Application
import androidx.lifecycle.Observer
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class HomeViewModel(
    application: Application,
    homeModel: HomeModel,
):
    GenericViewModel<HomeModel>(application, homeModel) {

    lateinit var githubRepositories: List<RepositoryItemQuery>

    private lateinit var repositoriesObserver: Observer<List<RepositoryItemQuery>>


    override fun fetch() {
        repositoriesObserver = Observer<List<RepositoryItemQuery>> {
            githubRepositories = it
        }
    }

    override fun addModelObservers() {
        model.githubRepositories.observeForever(repositoriesObserver)
    }

    override fun onCleared() {
        model.githubRepositories.removeObserver(repositoriesObserver)
        super.onCleared()
    }
}