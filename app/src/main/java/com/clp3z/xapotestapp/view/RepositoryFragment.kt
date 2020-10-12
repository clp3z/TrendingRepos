package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.clp3z.xapotestapp.repository.database.LocalDatabase
import com.clp3z.xapotestapp.viewmodel.RepositoryViewModel
import com.clp3z.xapotestapp.viewmodel.RepositoryViewModelFactory
import com.squareup.picasso.Picasso

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

        initializeObservers()

        return binding.root
    }

    private fun initializeViewModel() {
        val arguments = RepositoryFragmentArgs.fromBundle(requireArguments())
        val application = requireActivity().application
        val database = LocalDatabase.getInstance(application).databaseDao
        val viewModelFactory = RepositoryViewModelFactory(database, arguments.id)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RepositoryViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initializeObservers() {
        viewModel.repository.observe(viewLifecycleOwner, { repository ->
            binding.forksTextView.text = repository.forks.toString()
            binding.watchersTextView.text = repository.watchers.toString()
            binding.issuesTextView.text = repository.issues.toString()

            Picasso.get()
                .load(repository.owner.avatar)
                .placeholder(R.drawable.placeholder)
                .into(binding.avatarImageView)
        })
    }
}