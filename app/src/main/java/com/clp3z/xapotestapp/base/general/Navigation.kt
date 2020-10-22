package com.clp3z.xapotestapp.base.general

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clp3z.xapotestapp.home.HomeFragmentDirections

/**
 * Created by Clelia López on 9/23/20
 */
sealed class Navigation {

    abstract fun from(fragment: Fragment)

    // HomeFragment -> RepoFragment
    data class ToRepoFragment(val id: Int) : Navigation() {

        override fun from(fragment: Fragment) {
            fragment
                .findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToRepositoryFragment(id))
        }
    }
}