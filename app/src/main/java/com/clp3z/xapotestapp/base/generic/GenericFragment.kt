package com.clp3z.xapotestapp.base.generic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * Base class for any fragment in this MVVMB (Model-View-ViewModel-Binder) architecture
 * Its main responsibility is to instantiate the Binder {@property viewModelBinder}.
 *
 * In this architecture, a fragment is a View, and as such, part of the presentation layer.
 * So it should only focused on UI operations.
 *
 * Notes:
 * ViewDataBinding android classes are used.
 *
 * Created by Clelia López on 10/19/20
 */
abstract class GenericFragment<B: ViewDataBinding, VM: ViewModel>
    : Fragment() {

    protected lateinit var viewModelBinder: ViewModelBinder<B,VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        viewModelBinder.onBindViewModel()

        viewModelBinder.onBindObservers()

        return viewModelBinder.bindingObject.root
    }
}