package com.clp3z.xapotestapp.screen.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.databinding.ListItemBinding
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryAdapter(
    private val clickListener: RepositoryListener
):
    ListAdapter<RepositoryItemQuery, RepositoryAdapter.ViewHolder>(
        DiffCallback
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryItemQuery, clickListener: RepositoryListener) {
            binding.repository = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    object DiffCallback:
        DiffUtil.ItemCallback<RepositoryItemQuery>() {

        override fun areItemsTheSame(old: RepositoryItemQuery, new: RepositoryItemQuery) =
            old.id == new.id

        override fun areContentsTheSame(old: RepositoryItemQuery, new: RepositoryItemQuery) =
            old == new
    }

    class RepositoryListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(repository: RepositoryItemQuery) =
            clickListener(repository.id)
    }
}