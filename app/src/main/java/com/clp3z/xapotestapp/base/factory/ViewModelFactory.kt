package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.base.database.LocalDatabase
import com.clp3z.xapotestapp.home.HomeViewModel
import com.clp3z.xapotestapp.repo.RepoViewModel

/**
 * Created by Clelia López on 10/19/20
 */
class ViewModelFactory(
    private val application: Application,
    private val id: Int
)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {

        val database = LocalDatabase.getInstance(application).localDatabaseDao

        return when (modelClass) {

            HomeViewModel::class.java -> HomeViewModel(application, database) as T

            RepoViewModel::class.java -> RepoViewModel(application, database, id) as T

            else -> throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class")
        }
    }
}