package com.example.project.posts

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project.network.Post

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostAdapter
    adapter.submitList(data)
}
