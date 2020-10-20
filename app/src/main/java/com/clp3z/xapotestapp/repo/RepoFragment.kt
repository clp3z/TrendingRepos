package com.clp3z.xapotestapp.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding

/**
 * Created by Clelia López on 10/19/20
 */
class RepoFragment
    : GenericFragment<FragmentRepositoryBinding, RepoViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRepositoryBinding.inflate(inflater, container, false)

        viewModelBinder = RepoBinder(this, binding)

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}