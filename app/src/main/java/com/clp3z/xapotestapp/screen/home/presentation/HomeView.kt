package com.clp3z.xapotestapp.screen.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.base.generic.GenericView
import com.clp3z.xapotestapp.base.interfaces.Listener
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.screen.home.HomeFragment

/**
 * Created by Clelia López on 02/26/21
 */
class HomeView(
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
    viewModel: HomeViewModel,
    fragment: HomeFragment,
    private val listener: Listener.HomeViewListener
):
    GenericView<FragmentHomeBinding, HomeViewModel>(
        layoutInflater,
        container,
        viewModel,
        fragment
    ) {

    private val adapter = RepositoryAdapter(RepositoryAdapter.RepositoryListener { id ->
        listener.onItemSelected(id)
    })

    private val layoutManager = LinearLayoutManager(fragment.requireContext())

    private val lastVisibleItemPosition get() = layoutManager.findLastVisibleItemPosition()


    override fun initialize() {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = fragment
    }

    init {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(binding.recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount

                if (totalItemCount == lastVisibleItemPosition + 1)
                    listener.onFetchEvent()
            }
        })
    }
}