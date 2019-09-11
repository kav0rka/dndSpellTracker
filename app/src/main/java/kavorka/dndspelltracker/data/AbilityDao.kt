package kavorka.dndspelltracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AbilityDao {

    @Query("select * from ability where character = :character")
    fun getAbilitiesByCharacter(character: String): List<Ability>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ability: Ability)

    @Query("delete from ability where character = :character")
    fun deleteAbilitiesByCharacter(character: String)

    @Query("delete from ability where character = :character and name = :name")
    fun deleteAbilitiesByCharacterAndName(character: String, name: String)
}