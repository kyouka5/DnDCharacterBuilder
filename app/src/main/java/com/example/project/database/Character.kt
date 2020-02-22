package com.example.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "characters", foreignKeys = arrayOf(
        ForeignKey(
            entity = Party::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("party_id")
        )
    )
)
data class Character(
    @PrimaryKey(autoGenerate = true)
    var characterId: Long = 0L,

    @ColumnInfo(name = "party_id")
    var partyId: Long? = null,

    @ColumnInfo(name = "creation_time")
    var timeOfCreation: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "level")
    var level: Int = 1,

    @ColumnInfo(name = "experience_points")
    var experiencePoints: Int = 0,

    @ColumnInfo(name = "proficiency")
    var proficiency: Int = 2,

    @ColumnInfo(name = "race")
    var race: String = "",

    @ColumnInfo(name = "class")
    var chosenClass: String = "",

    @ColumnInfo(name = "alignment")
    var alignment: String = "",

    @ColumnInfo(name = "background")
    var background: String = "",

    @ColumnInfo(name = "armorClass")
    var armorClass: Int = 0,

    @ColumnInfo(name = "initiative")
    var initiative: Int = 0,

    @ColumnInfo(name = "speed")
    var speed: Int = 0,

    @ColumnInfo(name = "current_HP")
    var currentHP: Int = 0,

    @ColumnInfo(name = "max_HP")
    var maxHP: Int = 0,

    @ColumnInfo(name = "temp_HP")
    var tempHP: Int = 0,

    @ColumnInfo(name = "str")
    var str: Int = 0,

    @ColumnInfo(name = "dex")
    var dex: Int = 0,

    @ColumnInfo(name = "con")
    var con: Int = 0,

    @ColumnInfo(name = "inte")
    var inte: Int = 0,

    @ColumnInfo(name = "wis")
    var wis: Int = 0,

    @ColumnInfo(name = "cha")
    var cha: Int = 0,

    @ColumnInfo(name = "str_modifier")
    var strModifier: Int = 0,

    @ColumnInfo(name = "dex_modifier")
    var dexModifier: Int = 0,

    @ColumnInfo(name = "con_modifier")
    var conModifier: Int = 0,

    @ColumnInfo(name = "int_modifier")
    var intModifier: Int = 0,

    @ColumnInfo(name = "wis_modifier")
    var wisModifier: Int = 0,

    @ColumnInfo(name = "cha_modifier")
    var chaModifier: Int = 0
)