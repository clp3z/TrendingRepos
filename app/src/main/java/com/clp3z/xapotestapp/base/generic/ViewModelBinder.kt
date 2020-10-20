package com.clp3z.xapotestapp.base.generic

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.clp3z.properlytestapp.base.architecture.interfaces.BinderMethods
import com.clp3z.xapotestapp.base.database.DatabaseDAO
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.factory.ViewModelFactoryBuilder

/**
 *  It encapsulates the creation of the ViewModel and separates it from the fragment.
 *  In this way, the fragment's logic is kept minimal and consistent.
 *
 *  The ViewModel is created via the Factory Method pattern, so the instantiation falls upon
 *  the concrete class.
 *
 * Created by Clelia López on 10/19/20
 */

abstract class ViewModelBinder<B: ViewDataBinding, VM: ViewModel>(
    private var genericFragment: GenericFragment<B,VM>,
    var bindingObject: B
) :
    BinderMethods {

    protected lateinit var viewModel: ViewModel
    protected lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var modelFactoryCreator: ViewModelFactoryBuilder

    protected lateinit var database: DatabaseDAO
    protected lateinit var model: GenericModel<*>


    override fun onBindViewModel() {

        model = createModel()

        viewModelFactory = createViewModelFactory()

        viewModel = createViewModel()

        bindingObject.lifecycleOwner = genericFragment
    }
}