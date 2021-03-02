package com.clp3z.xapotestapp.screen.home.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import com.clp3z.xapotestapp.base.general.HomeViewState
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

    private val _repositories = model.repositories
    val repositories: LiveData<List<RepositoryItemQuery>> get() = _repositories

    private val _viewState = model.viewState
    val viewState: LiveData<HomeViewState> get() = _viewState

    override fun fetch() {}
    override fun addModelObservers() {}
}