package kavorka.dndspelltracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpellsDao {
    @Query("select * from spells where character = :character")
    fun getSpellsByCharacter(character: String): Spells

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spells: Spells)

    @Query("delete from spells where character = :character")
    fun deleteSpellsByCharacter(character: String)
}