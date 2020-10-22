package com.clp3z.xapotestapp.home

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.base.database.LocalDatabase
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.factory.ViewModelFactoryBuilder
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.ModelState
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.base.generic.ViewModelBinder
import com.clp3z.xapotestapp.databinding.FragmentMainBinding
import com.clp3z.xapotestapp.view.adapter.RepositoryAdapter
import com.clp3z.xapotestapp.view.adapter.RepositoryListener

/**
 * Created by Clelia López on 10/21/20
 */
class HomeBinder(
    private val fragment: HomeFragment,
    private val binding: FragmentMainBinding
):
    ViewModelBinder<FragmentMainBinding, HomeViewModel> (fragment, binding) {

    /**
     * Handles pagination
     */
    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    /**
     * Current page on web service
     */
    private var page = 1

    /**
     * LayoutManager
     */
    private lateinit var layoutManager: LinearLayoutManager

    /**
     * Application context
     */
    private var application: Application = fragment.requireActivity().application

    /**
     * Adapter
     */
    private lateinit var adapter: RepositoryAdapter


    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
        localDatabase = LocalDatabase.getInstance(application).localDatabaseDao

        initializeRecyclerView()
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
                    page += 1
                    (model as HomeModel).fetch(true, page)
                    logger.log("addOnScrollListener", "It's fetching page = $page")
                }
            }
        })
    }


    override fun createModel(): GenericModel<*> =
        HomeModel(localDatabase, application)


    override fun createViewModelFactory(): ViewModelFactory =
        ViewModelFactoryBuilder(application, model).build()


    override fun createViewModel(): ViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(HomeViewModel::class.java)


    override fun onBindViewModel() {
        super.onBindViewModel()
        binding.viewModel = viewModel as HomeViewModel
    }

    override fun onBindObservers() {

        // State observation on Model
        (model as HomeModel).state.observe(fragment, { state ->
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

                // TODO: create this message
                else -> logger.log("onBindObservers", "Error State!")
            }
        })

        // Adapter initialization via LiveData
        (viewModel as HomeViewModel).repositories.observe(fragment, { repositories ->
            adapter.submitList(repositories)
        })
    }

    private fun onItemSelected(id: Int) {
        fragment.findNavController().navigate(
            HomeFragmentDirections
            .actionMainFragmentToRepositoryFragment(id))
    }
}