package com.clp3z.xapotestapp.sections.home_old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.sections.home.HomeViewModel

/**
 * Created by Clelia López on 10/21/20
 */
class HomeFragment
    : GenericFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModelBinder = HomeBinder(this, binding)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}