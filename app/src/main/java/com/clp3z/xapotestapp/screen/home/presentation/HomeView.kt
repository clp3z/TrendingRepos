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
    private val listener: Listener.HomeViewListener,
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

    override fun initialize() {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = fragment
    }

    init {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                listener.onViewScroll(visibleItemCount, totalItemCount, firstVisibleItemPosition)
            }
        })
    }
}