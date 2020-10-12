package com.clp3z.xapotestapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clp3z.xapotestapp.R
import com.clp3z.xapotestapp.repository.model.Repository
import com.squareup.picasso.Picasso

/**
 * Created by Clelia López on 10/11/20
 */
class RepositoryAdapter:
    ListAdapter<Repository, RepositoryAdapter.ViewHolder>(RepositoryDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        /**
         * ItemView elements
         */
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val ownerTextView = view.findViewById<TextView>(R.id.ownerTextView)
        val forksTextView = view.findViewById<TextView>(R.id.forksTextView)
        val avatarImageView = view.findViewById<ImageView>(R.id.avatarImageView)

        /**
         * Bind views with contents
         */
        fun bind(item: Repository) {
            nameTextView.text = item.name
            ownerTextView.text = item.owner.login
            forksTextView.text = item.forks.toString()

            Picasso.get()
                .load(item.owner.avatar)
                .placeholder(R.drawable.placeholder)
                .into(avatarImageView);
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item, parent, false)
                return ViewHolder(view)
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