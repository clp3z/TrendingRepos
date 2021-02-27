package com.clp3z.xapotestapp.screen.home.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryDiffCallback: DiffUtil.ItemCallback<RepositoryItemQuery>() {

    override fun areItemsTheSame(
        oldItem: RepositoryItemQuery,
        newItem: RepositoryItemQuery
    )
        = newItem.id == oldItem.id


    override fun areContentsTheSame(
        oldItem: RepositoryItemQuery,
        newItem: RepositoryItemQuery
    )
        = newItem == oldItem

    // TODO: implement this in Generic
}