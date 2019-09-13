package kavorka.dndspelltracker

import androidx.room.Room

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import kavorka.dndspelltracker.data.PlayerCharacter
import kavorka.dndspelltracker.data.CharacterDatabase
import kavorka.dndspelltracker.data.Spells

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*



@RunWith(AndroidJUnit4::class)
class CharacterTests {
    val appContext = InstrumentationRegistry.getTargetContext()
    val db = Room.databaseBuilder(appContext, CharacterDatabase::class.java, "CharacterDatabase").build()

    val testCharacter = PlayerCharacter("Gideon", "", "", 1,8, 14, 14, 16, 12, 10)
    val testSpells = Spells(testCharacter.name, testCharacter.level, 1, 1)

    @Test
    fun characterTest() {
        db.clearAllTables()
        db.charactersDao().insert(testCharacter)
        val returnedCharacter = db.charactersDao().getCharacterByName(testCharacter.name)

        assertEquals(testCharacter, returnedCharacter)
    }

    @Test
    fun spellTest() {
        db.clearAllTables()
        db.charactersDao().insert(testCharacter)
        db.spellsDao().insert(testSpells)
        val returnedSpells = db.spellsDao().getSpellsByCharacter(testCharacter.name)

        assertEquals(testSpells, returnedSpells)
    }
}
