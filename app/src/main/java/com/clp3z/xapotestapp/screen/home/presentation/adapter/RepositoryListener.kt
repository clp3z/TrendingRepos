package com.clp3z.xapotestapp.screen.home.presentation.adapter

import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(repository: RepositoryItemQuery) {
        clickListener(repository.id)
    }
}