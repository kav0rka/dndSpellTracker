package kavorka.dndspelltracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Spells(
        @PrimaryKey
        val character: String,
        val slots: IntArray
)