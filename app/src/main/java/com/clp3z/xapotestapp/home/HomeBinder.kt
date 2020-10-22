package com.clp3z.xapotestapp.home

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.base.database.LocalDatabase
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.factory.ViewModelFactoryBuilder
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.ModelState
import com.clp3z.xapotestapp.base.general.Navigation
import com.clp3z.xapotestapp.base.generic.ViewModelBinder
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.home.adapter.RepositoryAdapter
import com.clp3z.xapotestapp.home.adapter.RepositoryListener

/**
 * Created by Clelia López on 10/21/20
 */
class HomeBinder(
    private val fragment: HomeFragment,
    private val binding: FragmentHomeBinding
):
    ViewModelBinder<FragmentHomeBinding, HomeViewModel> (fragment, binding) {

    /**
     * Adapter
     */
    private lateinit var adapter: RepositoryAdapter

    /**
     * LayoutManager
     */
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * Handles pagination
     */
    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    /**
     * Application context
     */
    private var application: Application = fragment.requireActivity().application

    /**
     * ViewModel reference
     */
    private lateinit var homeViewModel: HomeViewModel


    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
        localDatabase = LocalDatabase.getInstance(application).localDatabaseDao

        initializeRecyclerView()
    }

    override fun createViewModelFactory(): ViewModelFactory =
        ViewModelFactoryBuilder(application).build()


    override fun createViewModel(): ViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(HomeViewModel::class.java)


    override fun onBindViewModel() {
        super.onBindViewModel()
        homeViewModel = viewModel as HomeViewModel
        binding.viewModel = homeViewModel
    }

    override fun onBindObservers() {

        // State observation
        homeViewModel.getState().observe(fragment, { state ->
            when (state) {

                ModelState.LOADING -> {
                    binding.messageNoDatabase.view.visibility = View.VISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                    binding.messageError.view.visibility = View.INVISIBLE
                    binding.messageDownloading.view.visibility = View.INVISIBLE
                }

                ModelState.NO_INTERNET -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.VISIBLE
                    binding.messageError.view.visibility = View.INVISIBLE
                    binding.messageDownloading.view.visibility = View.INVISIBLE
                }

                ModelState.ERROR -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                    binding.messageError.view.visibility = View.VISIBLE
                    binding.messageDownloading.view.visibility = View.INVISIBLE
                }

                ModelState.DOWNLOADING -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                    binding.messageError.view.visibility = View.INVISIBLE
                    binding.messageDownloading.view.visibility = View.VISIBLE
                }

                else -> {
                    binding.messageNoDatabase.view.visibility = View.INVISIBLE
                    binding.messageNoConnection.view.visibility = View.INVISIBLE
                    binding.messageError.view.visibility = View.INVISIBLE
                    binding.messageDownloading.view.visibility = View.INVISIBLE
                }
            }
        })

        // Adapter initialization via LiveData
        homeViewModel.repositories.observe(fragment, { repositories ->
            adapter.submitList(repositories)
        })
    }

    private fun initializeRecyclerView() {

        // LayoutManager set up
        layoutManager = LinearLayoutManager(fragment.requireContext())
        binding.recyclerView.layoutManager = layoutManager

        // Adapter set up
        adapter = RepositoryAdapter(RepositoryListener { id ->
            onItemSelected(id)
        })
        binding.recyclerView.adapter = adapter

        // Endless pagination
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(binding.recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {

                    // Last item is visible now, increment page count and fetch  more repositories
                    homeViewModel.fetch(true)

                    logger.log("addOnScrollListener", "Should fetch now")
                }
            }
        })
    }

    private fun onItemSelected(id: Int) =
        Navigation.ToRepoFragment(id).from(fragment)
}