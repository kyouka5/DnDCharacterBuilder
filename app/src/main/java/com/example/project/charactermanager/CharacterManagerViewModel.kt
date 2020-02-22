package com.example.project.charactermanager

import android.app.Application
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.project.database.Character
import com.example.project.database.CharacterDAO
import kotlinx.coroutines.*

class CharacterManagerViewModel(
    dataSource: CharacterDAO
) : ViewModel() {
    val database = dataSource

    val allCharacters : LiveData<List<Character>> = database.getAllCharacters()

    private val _navigateToCharacterViewer = MutableLiveData<Long>()
    val navigateToCharacterViewer
        get() = _navigateToCharacterViewer

    private val _navigateToCharacterBuilder = MutableLiveData<Boolean?>()

    val navigateToCharacterBuilder: LiveData<Boolean?>
        get() = _navigateToCharacterBuilder

    fun onCharacterClicked(id: Long) {
        _navigateToCharacterViewer.value = id
    }

    fun onCharacterViewerNavigated() {
        _navigateToCharacterViewer.value = null
    }

    fun onCharacterBuilderClicked() {
        _navigateToCharacterBuilder.value = true
    }

    fun onCharacterBuilderNavigated() {
        _navigateToCharacterBuilder.value = null
    }

}