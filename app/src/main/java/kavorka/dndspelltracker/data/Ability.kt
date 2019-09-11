package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "name"])
data class Ability(
        val character: String,
        val name: String,
        val max: Int,
        var used: Int
)