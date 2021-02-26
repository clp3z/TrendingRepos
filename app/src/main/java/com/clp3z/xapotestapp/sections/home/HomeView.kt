package com.clp3z.xapotestapp.sections.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.base.general.Navigation
import com.clp3z.xapotestapp.base.generic.GenericView
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.model.RepositoryItemQuery
import com.clp3z.xapotestapp.sections.home.adapter.RepositoryAdapter
import com.clp3z.xapotestapp.sections.home.adapter.RepositoryListener

class HomeView(
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
    viewModel: HomeViewModel,
    fragment: HomeFragment
):
    GenericView<FragmentHomeBinding, HomeViewModel>(
        layoutInflater,
        container,
        viewModel,
        fragment
    ) {

    private val adapter = RepositoryAdapter(RepositoryListener { id ->
        onItemSelected(id)
    })

    private val layoutManager = LinearLayoutManager(fragment.requireContext())

    private val lastVisibleItemPosition get() = layoutManager.findLastVisibleItemPosition()


    // TODO: debug to check which init is executed first
    override fun initialize() {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = fragment
    }

    init {
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(binding.recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {

                    // TODO: require fetching from ViewModel
                }
            }
        })
    }

    fun addItems(items: List<RepositoryItemQuery>) {
        adapter.submitList(items)
    }


    // TODO: this a Navigation even, so it should be on HomeFragment
    private fun onItemSelected(id: Int) =
        Navigation.ToRepoFragment(id).from(fragment)
}