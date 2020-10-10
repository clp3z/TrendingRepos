package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.databinding.FragmentMainBinding
import com.clp3z.xapotestapp.repository.database.LocalDatabase
import com.clp3z.xapotestapp.viewmodel.MainViewModel
import com.clp3z.xapotestapp.viewmodel.MainViewModelFactory

/**
 * Created by Clelia López on 10/9/20
 */
class MainFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel:MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.messageNoDatabase.test.setOnClickListener(this)

        initializeViewModel()

        return binding.root
    }

    private fun initializeViewModel() {
        val application = requireActivity().application
        val database = LocalDatabase.getInstance(application).databaseDao
        val viewModelFactory = MainViewModelFactory(database, application)
        viewModel =  ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onClick(view: View) {
        view.findNavController().navigate(R.id.action_mainFragment_to_repositoryFragment)
        // view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToRepositoryFragment())
    }
}