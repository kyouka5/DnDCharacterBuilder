package com.example.project.characterbuilder

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.database.Character
import com.example.project.database.CharacterDAO
import com.example.project.database.Party
import com.example.project.database.PartyDAO
import kotlinx.coroutines.*

class CharacterBuilderViewModel(
    dataSource: CharacterDAO,
    parties: PartyDAO
) : ViewModel() {
    val database = dataSource

    val parties = parties

    var character: Character = Character()

    var partyName: String = ""

    private var viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToCharacterManager = MutableLiveData<Boolean?>()

    val navigateToCharacterManager: LiveData<Boolean?>
        get() = _navigateToCharacterManager

    fun doneNavigating() {
        _navigateToCharacterManager.value = null
    }

    fun onClose() {
        _navigateToCharacterManager.value = true
    }

    fun insert(character: Character) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                setPartyId(partyName)
                character.partyId = parties.getPartyByName(partyName)?.id
                setLevelAndProficiency(character)
                character.strModifier = setAbilityModifier(character.str)
                character.dexModifier = setAbilityModifier(character.dex)
                character.conModifier = setAbilityModifier(character.con)
                character.intModifier = setAbilityModifier(character.inte)
                character.wisModifier = setAbilityModifier(character.wis)
                character.chaModifier = setAbilityModifier(character.cha)
                database.insert(character)
            }
        }
        onClose()
    }

    fun setPartyId(name: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                var party = parties.getPartyByName(name)
                if (party != null) {
                    character.partyId = party.id
                } else {
                    party = Party()
                    party.name = name
                    parties.insert(party)
                }
            }
        }
    }

    fun setLevelAndProficiency(character: Character) {
        if (character.experiencePoints >= 0) {
            when (character.experiencePoints) {
                in 0..299 -> {
                    character.level = 1
                    character.proficiency = 2
                }
                in 300..899 -> {
                    character.level = 2
                    character.proficiency = 2
                }
                in 900..2699 -> {
                    character.level = 3
                    character.proficiency = 2
                }
                in 2700..6499 -> {
                    character.level = 4
                    character.proficiency = 2
                }
                in 6500..13999 -> {
                    character.level = 5
                    character.proficiency = 3
                }
                in 14000..22999 -> {
                    character.level = 6
                    character.proficiency = 3
                }
                in 23000..33999 -> {
                    character.level = 7
                    character.proficiency = 3
                }
                in 34000..47999 -> {
                    character.level = 8
                    character.proficiency = 3
                }
                in 48000..63999 -> {
                    character.level = 9
                    character.proficiency = 4
                }
                in 64000..84999 -> {
                    character.level = 10
                    character.proficiency = 4
                }
                in 85000..99999 -> {
                    character.level = 11
                    character.proficiency = 4
                }
                in 100000..119999 -> {
                    character.level = 12
                    character.proficiency = 4
                }
                in 120000..139999 -> {
                    character.level = 13
                    character.proficiency = 5
                }
                in 140000..164999 -> {
                    character.level = 14
                    character.proficiency = 5
                }
                in 165000..194999 -> {
                    character.level = 15
                    character.proficiency = 5
                }
                in 195000..224999 -> {
                    character.level = 16
                    character.proficiency = 5
                }
                in 225000..264999 -> {
                    character.level = 17
                    character.proficiency = 6
                }
                in 265000..304999 -> {
                    character.level = 18
                    character.proficiency = 6
                }
                in 305000..354999 -> {
                    character.level = 19
                    character.proficiency = 6
                }
                else -> {
                    character.level = 20
                    character.proficiency = 6
                }
            }
        } else {
            character.level = 1
            character.proficiency = 2
        }
    }

    fun setAbilityModifier(abilityScore: Int) : Int {
        return when (abilityScore) {
            in 2..3 -> -4
            in 4..5 -> -3
            in 6..7 -> -2
            in 8..9 -> -1
            in 10..11 -> 0
            in 12..13 -> 1
            in 14..15 -> 2
            in 16..17 -> 3
            in 18..19 -> 4
            in 20..21 -> 5
            else -> 0
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}