package com.example.project.characterviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project.database.CharacterDAO
import com.example.project.database.PartyDAO

class CharacterViewerViewModelFactory(
    private val characters: CharacterDAO,
    private val parties: PartyDAO,
    private val characterId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewerViewModel::class.java)) {
            return CharacterViewerViewModel(characters, parties, characterId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}