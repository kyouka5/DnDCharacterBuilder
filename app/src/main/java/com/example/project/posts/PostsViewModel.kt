package com.example.project.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.network.PostApi
import com.example.project.network.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val _properties = MutableLiveData<List<Post>>()

    val properties: LiveData<List<Post>>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPosts()
    }

    private fun getPosts() {
        coroutineScope.launch {
            var getPropertiesDeferred = PostApi.retrofitService.getProperties()
            try {
                val listResult = getPropertiesDeferred.await()
                _properties.value = listResult
            } catch (e: Exception) {
                _properties.value = emptyList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}