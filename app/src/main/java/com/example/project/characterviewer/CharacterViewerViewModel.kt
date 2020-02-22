package com.example.project.characterviewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.project.database.Character
import com.example.project.database.CharacterDAO
import com.example.project.database.Party
import com.example.project.database.PartyDAO
import kotlinx.coroutines.*
import java.text.DateFormat

class CharacterViewerViewModel(
    characters: CharacterDAO,
    parties: PartyDAO,
    characterId: Long
) : ViewModel() {
    val database = characters
    val parties = parties
    val charId = characterId

    var character: LiveData<Character>

    init {
        character = getCharacterById(charId)
    }

    var characterName = Transformations.map(character) { character ->
        character.name.toUpperCase()
    }

    var creationDate = Transformations.map(character) { character ->
        DateFormat.getInstance().format(character.timeOfCreation)
    }

    var party: MutableLiveData<Party> = MutableLiveData()

    private var viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateOnDelete =  MutableLiveData<Boolean?>()

    val navigateOnDelete: LiveData<Boolean?>
        get() = _navigateOnDelete

    fun getCharacterById(characterId: Long): LiveData<Character> {
        return database.getCharacterById(characterId)
    }

    fun delete() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteById(charId)
            }
        }
    }

    fun setPartyById(partyId: Long?) {
        var emptyParty = Party()
        party.value = emptyParty
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (partyId != null) {
                    party.postValue(parties.getPartyById(partyId))
                }
            }
        }
    }

    fun onDeleteClicked() {
        _navigateOnDelete.value = true
    }

    fun doneNavigating() {
        _navigateOnDelete.value = null
    }

}