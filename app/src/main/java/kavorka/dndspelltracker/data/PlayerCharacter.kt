package kavorka.dndspelltracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerCharacter(
        @PrimaryKey
        val name: String,
        var level: Int=1,
        var strength: Int=10,
        var dexterity: Int=10,
        var constitution: Int=10,
        var intelligence: Int=10,
        var wisdom: Int=10,
        var charisma: Int=10
)