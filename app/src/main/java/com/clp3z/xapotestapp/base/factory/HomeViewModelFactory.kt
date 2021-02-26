package com.clp3z.xapotestapp.base.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.sections.home.HomeModel
import com.clp3z.xapotestapp.sections.home.HomeViewModel

/**
 * Created by Clelia López on 02/26/21
 */
class HomeViewModelFactory(
    private val application: Application,
    private val homeModel: HomeModel
):
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java ->
                HomeViewModel(application, homeModel) as T

            else ->
                throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel class")
        }
    }
}