package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.home.HomeModel
import com.clp3z.xapotestapp.home.HomeViewModel
import com.clp3z.xapotestapp.repo.RepoModel
import com.clp3z.xapotestapp.repo.RepoViewModel

/**
 * Created by Clelia López on 10/19/20
 */
class ViewModelFactory(
    private val application: Application,
    private val model: GenericModel<*>
):
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>) =  when (modelClass) {

        RepoViewModel::class.java -> RepoViewModel(application, model as RepoModel) as T

        HomeViewModel::class.java -> HomeViewModel(application, model as HomeModel) as T

        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}