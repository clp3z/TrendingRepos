package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.base.generic.GenericModel
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
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when (modelClass) {

            RepoViewModel::class.java -> return RepoViewModel(application, model as RepoModel) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}