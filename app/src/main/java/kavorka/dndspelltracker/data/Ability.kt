package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "name"])
data class Ability(
        var character: String,
        val name: String,
        val max: Int,
        var used: Int=0
)