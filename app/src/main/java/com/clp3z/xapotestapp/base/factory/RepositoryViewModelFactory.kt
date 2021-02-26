package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.sections.repository.RepositoryModel
import com.clp3z.xapotestapp.sections.repository.RepositoryViewModelR

class RepositoryViewModelFactory(
    private val application: Application,
    private val repositoryModel: RepositoryModel
):
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {


        return when (modelClass) {

            RepositoryViewModelR::class.java -> RepositoryViewModelR(application, repositoryModel) as T

            else -> throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class")
        }
    }
}