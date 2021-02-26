package com.clp3z.xapotestapp.sections.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clp3z.xapotestapp.base.factory.RepositoryViewModelFactory
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding
import com.clp3z.xapotestapp.repository.database.client.LocalDatabase
import com.clp3z.xapotestapp.sections.repository.domain.RepositoryDAO
import com.clp3z.xapotestapp.sections.repository.domain.RepositoryModel
import com.clp3z.xapotestapp.sections.repository.presentation.RepositoryView
import com.clp3z.xapotestapp.sections.repository.presentation.RepositoryViewModel

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryFragment
    : GenericFragment<FragmentRepositoryBinding, RepositoryViewModel>() {


    private val application = requireActivity().application
    private val repositoryRoomDAO = LocalDatabase.getInstance(application).repositoryRoomDAO

    private val arguments = RepositoryFragmentArgs.fromBundle(requireArguments())
    private val repositoryDAO = RepositoryDAO(repositoryRoomDAO, arguments.id)
    private val model = RepositoryModel(repositoryDAO)

    private val viewModelFactory = RepositoryViewModelFactory(application, model)
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