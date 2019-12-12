package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "name"])
data class Ability(
        var name: String,
        var max: Int=1,
        var used: Int=0,
        var resetOnShort: Boolean=false,
        val resetOnLong: Int=max,
        var additionalInfo: String="",
        var type: String="",
        var character: String=""
)