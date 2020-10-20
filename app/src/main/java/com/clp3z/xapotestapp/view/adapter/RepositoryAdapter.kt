package com.clp3z.xapotestapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.databinding.ListItemBinding
import com.clp3z.xapotestapp.repository.database.Repository
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 10/11/20
 */
class RepositoryAdapter(private val clickListener: RepositoryListener):
    ListAdapter<Repository, RepositoryAdapter.ViewHolder>(RepositoryDiffCallback()){

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
        fun bind(item: Repository, clickListener: RepositoryListener) {
            binding.repository = item

            binding.nameTextView.text = item.name
            binding.ownerTextView.text = item.owner.login
            binding.forksTextView.text = item.forks.toString()

            Picasso.get()
                .load(item.owner.avatar)
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

    class RepositoryDiffCallback: DiffUtil.ItemCallback<Repository>() {

        /**
         * Two items are the same if their Id values are equal
         */
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
            newItem.id == oldItem.id

        /**
         * Two items are the same if they have the same value, oldItem == newItem.
         * Validation with .equals (via data class implementation)
         */
        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
            newItem == oldItem
    }
}

class RepositoryListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(repository: Repository) {
        clickListener(repository.id)
    }
}