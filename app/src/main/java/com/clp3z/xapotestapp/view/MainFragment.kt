package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.base.ModelState
import com.clp3z.xapotestapp.databinding.FragmentMainBinding
import com.clp3z.xapotestapp.repository.database.LocalDatabase
import com.clp3z.xapotestapp.view.adapter.RepositoryAdapter
import com.clp3z.xapotestapp.viewmodel.MainViewModel
import com.clp3z.xapotestapp.viewmodel.MainViewModelFactory

/**
 * Created by Clelia López on 10/9/20
 */
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel:MainViewModel
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * Handles pagination
     */
    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    /**
     * Current page on web service
     */
    private var page = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        initializeViewModel()

        initializeRecyclerView()

        initializeObservers()

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

    private fun initializeRecyclerView() {

        // LayoutManager set up
        layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager

        // Adapter set up
        val adapter = RepositoryAdapter()
        binding.recyclerView.adapter = adapter

        // Adapter initialization via LiveData
        viewModel.repositories.observe(viewLifecycleOwner, { repositories ->
            if (repositories.isEmpty())
                viewModel.fetch()
            else
                adapter.submitList(repositories)
        })

        // Endless pagination
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(binding.recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {

                    // Last item is visible now, increment page count and fetch  more repositories
                    page = page + 1
                    viewModel.fetch(onPagination = true, page)
                }
            }
        })
    }

    private fun initializeObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                ModelState.NO_INTERNET -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.VISIBLE
                }

                ModelState.LOADING -> {
                    binding.messageNoDatabase.view.visibility = View.VISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                }

                ModelState.AVAILABLE -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                }

                else -> println("THIS IS PENDING")
            }
        })
    }
}