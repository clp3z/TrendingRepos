package com.clp3z.xapotestapp.base.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clp3z.xapotestapp.screen.home.HomeFragmentDirections

/**
 * Created by Clelia López on 02/26/21
 */
sealed class Navigation {

    abstract fun from(fragment: Fragment)


    data class ToRepositoryFragment(val id: Int) : Navigation() {

        override fun from(fragment: Fragment) {
            fragment
                .findNavController()
                .navigate(HomeFragmentDirections
                .actionHomeFragmentToRepositoryFragment(id))
        }
    }
}