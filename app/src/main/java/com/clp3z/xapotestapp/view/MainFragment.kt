package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.databinding.FragmentMainBinding

/**
 * Created by Clelia López on 10/9/20
 */
class MainFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    //private lateinit var gameViewModel:GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.messageNoDatabase.test.setOnClickListener(this)

        initializeViewModel()

        return binding.root
    }

    private fun initializeViewModel() {

    }

    override fun onClick(view: View) {
        view.findNavController().navigate(R.id.action_mainFragment_to_repositoryFragment)
        view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToRepositoryFragment())
    }
}