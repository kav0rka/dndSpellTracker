package kavorka.dndspelltracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerCharacter(
        @PrimaryKey
        val name: String,
        val characterClass: String,
        val characterSubClass: String,
        var level: Int,
        var strength: Int,
        var dexterity: Int,
        var constitution: Int,
        var intelligence: Int,
        var wisdom: Int,
        var charisma: Int,
        var maxHP: Int=10,
        var hp: Int=10
)