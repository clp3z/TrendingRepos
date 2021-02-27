package com.clp3z.xapotestapp.screen.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.clp3z.xapotestapp.repository.database.client.LocalDatabase
import com.clp3z.xapotestapp.screen.repository.domain.RepositoryDAO
import com.clp3z.xapotestapp.screen.repository.domain.RepositoryModel
import com.clp3z.xapotestapp.screen.repository.presentation.RepositoryView
import com.clp3z.xapotestapp.screen.repository.presentation.RepositoryViewModel

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryFragment
    : GenericFragment<FragmentRepositoryBinding, RepositoryViewModel>() {


    private val application = requireActivity().application
    private val repositoryRoomDAO = LocalDatabase.getInstance(application).repositoryRoomDAO

    private val arguments = RepositoryFragmentArgs.fromBundle(requireArguments())
    private val repositoryDAO = RepositoryDAO(repositoryRoomDAO)
    private val model = RepositoryModel(repositoryDAO, arguments.id)

    private val viewModelFactory = ViewModelFactory(application, model)
    private val viewModel by viewModels<RepositoryViewModel> { viewModelFactory }

    private lateinit var view: RepositoryView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = RepositoryView(inflater, container, viewModel, this)
        return view.root
    }
}