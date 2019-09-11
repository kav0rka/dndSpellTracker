package kavorka.dndspelltracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpellsDao {
    @Query("select * from spells where character = :character")
    fun getSpellsByCharacterLive(character: String): LiveData<List<Spells>>

    @Query("select * from spells where character = :character")
    fun getSpellsByCharacter(character: String): List<Spells>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spells: Spells)

    @Query("delete from spells where character = :character")
    fun deleteSpellsByCharacter(character: String)
}