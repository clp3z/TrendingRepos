package com.clp3z.xapotestapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.clp3z.xapotestapp.base.generic.GenericView
import com.clp3z.xapotestapp.databinding.FragmentRepositoryRefactorBinding
import com.clp3z.xapotestapp.sections.repository.RepositoryFragment
import com.clp3z.xapotestapp.sections.repository.RepositoryViewModelR

class RepositoryView(
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
    viewModel: RepositoryViewModelR,
    fragment: RepositoryFragment
):
    GenericView<FragmentRepositoryRefactorBinding, RepositoryViewModelR>(
        layoutInflater,
        container,
        viewModel,
        fragment
    ) {

    init {
        binding = FragmentRepositoryRefactorBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = fragment
    }
}