


package com.clp3z.xapotestapp.base.generic

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.base.general.Logger

/**
 * Created by Clelia López on 02/26/21
 */
@Suppress("PropertyName")
abstract class GenericFragment<B: ViewDataBinding, VM: ViewModel>
    : Fragment() {


    protected lateinit var tagClass: String
    protected lateinit var logger: Logger
}