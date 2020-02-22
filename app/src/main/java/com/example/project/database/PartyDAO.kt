package com.example.project.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartyDAO {
    @Insert
    fun insert(party: Party)

    @Update
    fun update(party: Party)

    @Query("select * from parties where name = :name")
    fun getPartyByName(name: String) : Party?

    @Query("select * from parties where id = :id")
    fun getPartyById(id: Long) : Party?

    @Query("select * from parties")
    fun getAllParties() : LiveData<List<Party>>

    @Delete
    fun delete(party: Party)
}