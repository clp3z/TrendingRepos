package com.clp3z.xapotestapp.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clp3z.xapotestapp.base.factory.HomeViewModelFactory
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.repository.database.client.LocalDatabase
import com.clp3z.xapotestapp.repository.network.client.RetrofitClient
import com.clp3z.xapotestapp.screen.home.domain.HomeDAO
import com.clp3z.xapotestapp.screen.home.domain.HomeModel
import com.clp3z.xapotestapp.screen.home.domain.HomeRepository
import com.clp3z.xapotestapp.screen.home.domain.HomeRequest
import com.clp3z.xapotestapp.screen.home.presentation.*

/**
 * Created by Clelia López on 10/21/20
 */
class HomeFragment
    : GenericFragment<FragmentHomeBinding, HomeViewModel>() {


    private val application = requireActivity().application
    private val repositoryRoomDAO = LocalDatabase.getInstance(application).repositoryRoomDAO
    private val webservice = RetrofitClient.webservice

    private val homeDAO = HomeDAO(repositoryRoomDAO)
    private val homeRequest = HomeRequest(webservice)
    private val homeRepository = HomeRepository(homeDAO, homeRequest)
    private val model = HomeModel(homeRepository)

    private val viewModelFactory = HomeViewModelFactory(application, model)
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var view: HomeView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = HomeView(inflater, container, viewModel, this)
        return view.root
    }
}