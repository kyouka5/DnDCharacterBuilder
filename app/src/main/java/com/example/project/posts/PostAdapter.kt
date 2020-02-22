package com.example.project.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.ListViewItemPostBinding
import com.example.project.network.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostPropertyViewHolder>(DiffCallback) {

    class PostPropertyViewHolder(private var binding: ListViewItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.property = post
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostPropertyViewHolder {
        return PostPropertyViewHolder(ListViewItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostPropertyViewHolder, position: Int) {
        val postProperty = getItem(position)
        holder.bind(postProperty)
    }
}