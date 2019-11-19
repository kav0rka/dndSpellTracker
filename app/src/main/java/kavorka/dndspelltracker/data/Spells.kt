package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "level"])
data class Spells(
        val character: String,
        val level: Int,
        val max: Int,
        var used: Int,
        val resetOnShort: Boolean = false
)