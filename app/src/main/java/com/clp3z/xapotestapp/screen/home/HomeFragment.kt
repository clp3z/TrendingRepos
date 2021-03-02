package com.clp3z.xapotestapp.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.clp3z.xapotestapp.XapoApplication
import com.clp3z.xapotestapp.base.factory.ViewModelFactory
import com.clp3z.xapotestapp.base.generic.GenericFragment
import com.clp3z.xapotestapp.base.interfaces.Listener
import com.clp3z.xapotestapp.base.util.Navigation
import com.clp3z.xapotestapp.databinding.FragmentHomeBinding
import com.clp3z.xapotestapp.MainActivity
import com.clp3z.xapotestapp.repository.database.client.LocalDatabase
import com.clp3z.xapotestapp.repository.database.client.RepositoryRoomDAO
import com.clp3z.xapotestapp.repository.network.client.RetrofitClient
import com.clp3z.xapotestapp.screen.home.domain.HomeDAO
import com.clp3z.xapotestapp.screen.home.domain.HomeModel
import com.clp3z.xapotestapp.screen.home.domain.HomeRepository
import com.clp3z.xapotestapp.screen.home.domain.HomeNetworkRequest
import com.clp3z.xapotestapp.screen.home.presentation.*

/**
 * Created by Clelia López on 10/21/20
 */
class HomeFragment
    : GenericFragment<FragmentHomeBinding, HomeViewModel>(),
    Listener.HomeViewListener {


    private lateinit var application: XapoApplication
    private lateinit var repositoryRoomDAO: RepositoryRoomDAO
    private val webservice = RetrofitClient.WEBSERVICE

    private lateinit var dao: HomeDAO
    private lateinit var networkRequest: HomeNetworkRequest
    private lateinit var repository: HomeRepository
    private lateinit var model: HomeModel

    private lateinit var viewModelFactory: ViewModelFactory<HomeModel>
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var view: HomeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        application = (requireActivity() as MainActivity).application as XapoApplication
        repositoryRoomDAO = LocalDatabase.getInstance(application).repositoryRoomDAO

        dao = HomeDAO(repositoryRoomDAO)
        networkRequest = HomeNetworkRequest(webservice)
        repository = HomeRepository(application, dao, networkRequest)
        model = HomeModel(repository)

        viewModelFactory = ViewModelFactory(application, model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        view = HomeView(inflater, container, viewModel, this, this)
        return view.root
    }

    override fun onFetchEvent() {
        repository.fetch()
    }

    override fun onItemSelected(id: Int) {
        Navigation.ToRepositoryFragment(id).from(this)
    }
}