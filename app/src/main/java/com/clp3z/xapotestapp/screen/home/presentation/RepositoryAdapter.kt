package com.clp3z.xapotestapp.screen.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.databinding.ListItemBinding
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryAdapter(
    private val clickListener: RepositoryListener
):
    ListAdapter<RepositoryItemQuery, RepositoryAdapter.ViewHolder>(
        RepositoryDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder(private val binding: ListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        /**
         * Bind views with contents
         */
        fun bind(item: RepositoryItemQuery, clickListener: RepositoryListener) {
            binding.repository = item

            binding.nameTextView.text = item.name
            binding.ownerTextView.text = item.owner_login
            binding.forksTextView.text = item.forks.toString()

            Picasso.get()
                .load(item.owner_avatar)
                .placeholder(R.drawable.placeholder)
                .into(binding.avatarImageView)

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

    class RepositoryDiffCallback: DiffUtil.ItemCallback<RepositoryItemQuery>() {

        override fun areItemsTheSame(
            oldItem: RepositoryItemQuery,
            newItem: RepositoryItemQuery
        ) = newItem.id == oldItem.id


        override fun areContentsTheSame(
            oldItem: RepositoryItemQuery,
            newItem: RepositoryItemQuery
        ) = newItem == oldItem
    }

    class RepositoryListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(repository: RepositoryItemQuery) {
            clickListener(repository.id)
        }
    }
}