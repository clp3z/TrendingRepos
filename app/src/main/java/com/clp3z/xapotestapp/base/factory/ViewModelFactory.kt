package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.screen.home.domain.HomeModel
import com.clp3z.xapotestapp.screen.home.presentation.HomeViewModel
import com.clp3z.xapotestapp.screen.repository.domain.RepositoryModel
import com.clp3z.xapotestapp.screen.repository.presentation.RepositoryViewModel

/**
 * Created by Clelia López on 02/27/21
 */
class ViewModelFactory<M: GenericModel<*>>(
    private val application: Application,
    private val model: M
):
    ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("unchecked_cast")
        return when (modelClass) {

            RepositoryViewModel::class.java ->
                RepositoryViewModel(application, model as RepositoryModel) as T

            HomeViewModel::class.java ->
                HomeViewModel(application, model as HomeModel) as T

            else ->
                throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class")
        }
    }
}