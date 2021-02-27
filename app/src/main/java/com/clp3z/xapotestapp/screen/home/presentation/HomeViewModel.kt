package com.clp3z.xapotestapp.screen.home.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.screen.home.domain.HomeModel

/**
 * Created by Clelia López on 02/26/21
 */
class HomeViewModel(
    application: Application,
    homeModel: HomeModel,
):
    GenericViewModel<HomeModel>(application, homeModel) {

    private val _githubRepositories = model.repositories
    val githubRepositories: LiveData<List<RepositoryItemQuery>> get() = _githubRepositories

    override fun fetch() {}
    override fun addModelObservers() {}
}