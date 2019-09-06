package kavorka.dndspelltracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerCharacter::class, Spells::class], version = 1, exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun spellsDao(): SpellsDao
}