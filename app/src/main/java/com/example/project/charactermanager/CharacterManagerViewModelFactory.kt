package com.example.project.charactermanager

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.characterbuilder.CharacterBuilderViewModel
import com.example.project.database.CharacterDAO

class CharacterManagerViewModelFactory(
    private val dataSource: CharacterDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterManagerViewModel::class.java)) {
            return CharacterManagerViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}