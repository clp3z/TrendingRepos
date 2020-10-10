package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.clp3z.xapotestapp.repository.database.LocalDatabase
import com.clp3z.xapotestapp.viewmodel.RepositoryViewModel
import com.clp3z.xapotestapp.viewmodel.RepositoryViewModelFactory

/**
 * Created by Clelia López on 10/9/20
 */
class RepositoryFragment: Fragment() {

    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var viewModel:RepositoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRepositoryBinding.inflate(inflater, container, false)

        initializeViewModel()

        return binding.root
    }

    private fun initializeViewModel() {
        val id = 0 // TODO get from safeargs
        val application = requireActivity().application
        val database = LocalDatabase.getInstance(application).databaseDao
        val viewModelFactory = RepositoryViewModelFactory(database, id)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RepositoryViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}