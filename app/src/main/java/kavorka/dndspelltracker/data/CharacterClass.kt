package kavorka.dndspelltracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["character", "name"])
data class CharacterClass(
        var name: String,
        var subClass: String,
        var character: String="",
        var level: Int=1
)