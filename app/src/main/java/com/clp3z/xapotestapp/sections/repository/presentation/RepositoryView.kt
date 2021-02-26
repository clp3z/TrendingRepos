package com.clp3z.xapotestapp.sections.repository.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.clp3z.xapotestapp.base.generic.GenericView
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.clp3z.xapotestapp.sections.repository.RepositoryFragment

class RepositoryView(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        viewModel: RepositoryViewModel,
        fragment: RepositoryFragment
):
    GenericView<FragmentRepositoryBinding, RepositoryViewModel>(
        layoutInflater,
        container,
        viewModel,
        fragment
    ) {

    override fun initialize() {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = fragment
    }
}