package com.clp3z.properlytestapp.base.architecture.interfaces

import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.generic.GenericModel

/**
 * Created by Clelia López on 10/19/20
 */
interface BinderMethods {

    fun createModel(): GenericModel<*>

    fun createViewModelFactory(): ViewModelFactory

    fun createViewModel(): ViewModel

    fun onBindViewModel()

    fun onBindObservers()
}