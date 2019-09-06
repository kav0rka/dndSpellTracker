package kavorka.dndspelltracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Spells(
        @PrimaryKey
        val character: String,
        val lvl1: Int=0,
        val lvl2: Int=0,
        val lvl3: Int=0,
        val lvl4: Int=0,
        val lvl5: Int=0,
        val lvl6: Int=0,
        val lvl7: Int=0,
        val lvl8: Int=0,
        val lvl9: Int=0
)