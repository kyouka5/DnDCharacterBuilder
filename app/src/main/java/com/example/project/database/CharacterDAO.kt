package com.example.project.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface CharacterDAO {
    @Insert
    fun insert(character: Character)

    @Update
    fun update(character: Character)

    @Query("select * from characters where characterId = :id")
    fun getCharacterById(id: Long) : LiveData<Character>

    @Query("select * from characters")
    fun getAllCharacters() : LiveData<List<Character>>

    @Delete
    fun delete(character: Character)

    @Query("delete from characters where characterId = :characterId")
    fun deleteById(characterId: Long)
}