package com.clp3z.xapotestapp.base.generic

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.base.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.factory.ViewModelFactoryBuilder
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.interfaces.BinderMethods

/**
 *  It encapsulates the creation of the ViewModel and separates it from the fragment.
 *  In this way, the fragment's logic is kept minimal and consistent.
 *
 *  The ViewModel is created via the Factory Method pattern, so the instantiation falls upon
 *  the concrete class.
 *
 * Created by Clelia López on 10/19/20
 */

@Suppress("PropertyName")
abstract class ViewModelBinder<B: ViewDataBinding, VM: ViewModel>(
    private var genericFragment: GenericFragment<B,VM>,
    var bindingObject: B
) :
    BinderMethods {

    /**
     * ViewModel and Factories associated
     */
    protected lateinit var viewModel: ViewModel
    protected lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var modelFactoryCreator: ViewModelFactoryBuilder

    /**
     * DAO (Later on referred as database)
     */
    protected lateinit var localDatabase: LocalDatabaseDAO

    /**
     * Logger
     */
    protected lateinit var TAG: String
    protected lateinit var logger: Logger


    override fun onBindViewModel() {

        viewModelFactory = createViewModelFactory()

        viewModel = createViewModel()

        bindingObject.lifecycleOwner = genericFragment
    }
}