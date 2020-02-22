package com.example.project.characterbuilder

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.database.CharacterDAO
import com.example.project.database.PartyDAO

class CharacterBuilderViewModelFactory(
    private val dataSource: CharacterDAO,
    private val parties: PartyDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterBuilderViewModel::class.java)) {
            return CharacterBuilderViewModel(dataSource, parties) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}