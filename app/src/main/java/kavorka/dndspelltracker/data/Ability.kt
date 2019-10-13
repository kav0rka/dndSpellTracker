package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "name"])
data class Ability(
        var character: String,
        var name: String,
        val max: Int,
        var used: Int=0,
        var resetOnShort: Boolean=false,
        val resetOnLong: Int=max,
        var additionalInfo: String=""
)