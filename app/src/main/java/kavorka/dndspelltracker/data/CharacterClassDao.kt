package kavorka.dndspelltracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterClassDao {

    @Query("select * from characterClass where character = :character")
    fun getClassesByCharacter(character: String): List<CharacterClass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterClass: CharacterClass)

    @Query("delete from characterClass where character = :character")
    fun deleteClassesByCharacter(character: String)

    @Query("delete from characterClass where character = :character and name = :name")
    fun deleteClassByCharacterAndName(character: String, name: String)
}