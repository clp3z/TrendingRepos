package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.screen.repository.domain.RepositoryModel
import com.clp3z.xapotestapp.screen.repository.presentation.RepositoryViewModel

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryViewModelFactory(
    private val application: Application,
    private val model: RepositoryModel
):
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            RepositoryViewModel::class.java ->
                RepositoryViewModel(application, model) as T

            else ->
                throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class")
        }
    }
}