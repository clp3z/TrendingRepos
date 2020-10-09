package com.clp3z.xapotestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clp3z.xapotestapp.databinding.FragmentRepositoryBinding

/**
 * Created by Clelia López on 10/9/20
 */
class RepositoryFragment: Fragment() {

    private lateinit var binding: FragmentRepositoryBinding
    //private lateinit var gameViewModel:GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        binding = FragmentRepositoryBinding.inflate(inflater, container, false)

        initializeViewModel()

        return binding.root
    }

    private fun initializeViewModel() {

    }
}