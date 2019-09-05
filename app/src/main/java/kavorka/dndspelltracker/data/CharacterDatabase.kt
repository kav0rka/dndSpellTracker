package kavorka.dndspelltracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class, Spells::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun spellsDao(): SpellsDao
}