package com.clp3z.xapotestapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentMainBinding

/**
 * Created by Clelia López on 10/21/20
 */
class HomeFragment
    : GenericFragment<FragmentMainBinding, HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModelBinder = HomeBinder(this, binding)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}