package com.clp3z.xapotestapp.base.interfaces

import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.base.factory.ViewModelFactory

/**
 * Created by Clelia López on 10/19/20
 */
interface BinderMethods {

    fun createViewModelFactory(): ViewModelFactory

    fun createViewModel(): ViewModel

    fun onBindViewModel()

    fun onBindObservers()
}