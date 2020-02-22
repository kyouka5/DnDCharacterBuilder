package com.example.project.posts


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.project.databinding.FragmentPostsBinding

/**
 * A simple [Fragment] subclass.
 */
class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostsBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.postsViewModel = viewModel

        binding.postsRecyclerview.adapter = PostAdapter()

        return binding.root
    }


}
